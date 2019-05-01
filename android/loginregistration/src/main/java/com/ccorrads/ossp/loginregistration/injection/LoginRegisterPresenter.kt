package com.ccorrads.ossp.loginregistration.injection

import android.util.Log
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import com.ccorrads.ossp.core.database.models.Auth
import com.ccorrads.ossp.core.database.models.User
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.core.network.models.LoginRequest
import com.ccorrads.ossp.core.network.models.UserResponse
import com.ccorrads.ossp.core.network.observers.SingleErrorHandlingObserver
import com.ccorrads.ossp.loginregistration.AuthMvp
import com.ccorrads.ossp.loginregistration.views.ValidatableText
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class LoginRegisterPresenter
@Inject constructor(
    private val networkUtil: NetworkUtil,
    private val backendService: BackendService,
    private val authDao: AuthDao,
    private val userDao: UserDao,
    private val rxSchedulers: NetworkModule.RxSchedulers
) : AuthMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun validateViews(emailView: ValidatableText, pwView: ValidatableText): Boolean {
        return emailView.validate() && pwView.validate()
    }

    private fun getObserver(loginView: AuthMvp.View): SingleErrorHandlingObserver<UserResponse> {
        return object : SingleErrorHandlingObserver<UserResponse>(networkUtil, disposable, loginView) {
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
                loginView.hideProgress()
                loginView.onAuthenticated()
            }

            override fun onError(t: Throwable) {
                super.onError(t)
                Log.e(LoginRegisterPresenter::class.java.simpleName, t.message, t)
            }
        }
    }

    override fun onClickLogin(loginView: AuthMvp.View, user: String?, pw: String?) {
        backendService.loginUser(
            LoginRequest(username = user, password = pw)
        ).subscribeOn(rxSchedulers.disk)
            .observeOn(rxSchedulers.main)
            .subscribe(getObserver(loginView))
    }

    override fun onClickRegister(loginView: AuthMvp.View) {
        loginView.navigateToRegistration()
    }

    override fun cancel() {
        disposable.clear()
    }
}