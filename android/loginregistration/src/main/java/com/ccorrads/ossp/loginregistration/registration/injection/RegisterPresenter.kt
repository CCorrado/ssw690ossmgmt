package com.ccorrads.ossp.loginregistration.registration.injection

import android.util.Log
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import com.ccorrads.ossp.core.database.models.Auth
import com.ccorrads.ossp.core.database.models.User
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.core.network.models.RegisterRequest
import com.ccorrads.ossp.core.network.models.UserResponse
import com.ccorrads.ossp.core.network.observers.SingleErrorHandlingObserver
import com.ccorrads.ossp.loginregistration.registration.RegisterMvp
import com.ccorrads.ossp.loginregistration.views.ValidatableText
import io.reactivex.disposables.CompositeDisposable
import org.joda.time.DateTime
import java.util.*
import javax.inject.Inject

class RegisterPresenter
@Inject constructor(
    private val backendService: BackendService,
    private val rxSchedulers: NetworkModule.RxSchedulers,
    private val networkUtil: NetworkUtil,
    private val userDao: UserDao,
    private val authDao: AuthDao
) : RegisterMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun validateViews(
        emailView: ValidatableText,
        pwView: ValidatableText,
        repeatPwView: ValidatableText,
        nameView: ValidatableText
    ): Boolean {
        return emailView.validate() && pwView.validate() && repeatPwView.validate() && nameView.validate()
    }

    override fun registerUser(registerView: RegisterMvp.View, username: String?, pw: String?, fullName: String?) {
        backendService.registerUser(
            RegisterRequest(
                username = username,
                password = pw,
                name = fullName,
                createDate = DateTime.now(),
                role = "Consumer"
            )
        ).observeOn(rxSchedulers.main).subscribeOn(rxSchedulers.disk).subscribe(getObserver(registerView))
    }

    private fun getObserver(registerView: RegisterMvp.View): SingleErrorHandlingObserver<UserResponse> {
        return object : SingleErrorHandlingObserver<UserResponse>(networkUtil, disposable, registerView) {
            override fun onSuccess(t: UserResponse) {
                super.onSuccess(t)
                authDao.clear()
                userDao.clear()
                userDao.insertUser(
                    User(
                        username = t.username,
                        dbId = t.userId,
                        type = t.role,
                        role = User.UserRole.Consumer,
                        fullName = t.name,
                        id = UUID.randomUUID().toString(),
                        age = t.userCreatedDate.toDateTimeISO().toString(),
                        createdDate = t.userCreatedDate.toDateTimeISO().toString()
                    )
                )
                authDao.insertAuth(
                    Auth(
                        accessToken = t.accessToken,
                        refreshToken = t.refreshToken,
                        userId = t.userId,
                        id = t.sessionId,
                        createdDate = t.sessionCreatedDate.toDateTimeISO().toString()
                    )
                )
                registerView.hideProgress()
                registerView.onAuthenticated()
            }

            override fun onError(t: Throwable) {
                super.onError(t)
                Log.e(RegisterPresenter::class.java.simpleName, t.message, t)
            }
        }
    }

    override fun cancel() {
        disposable.clear()
    }
}