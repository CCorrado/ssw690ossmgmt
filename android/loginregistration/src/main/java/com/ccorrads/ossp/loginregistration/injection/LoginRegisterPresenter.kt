package com.ccorrads.ossp.loginregistration.injection

import android.util.Log
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.models.Auth
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.core.network.models.LoginRequest
import com.ccorrads.ossp.core.network.observers.SingleErrorHandlingObserver
import com.ccorrads.ossp.loginregistration.AuthMvp
import com.ccorrads.ossp.loginregistration.views.ValidatableText
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginRegisterPresenter
@Inject constructor(
    private val networkUtil: NetworkUtil,
    private val backendService: BackendService,
    private val authDao: AuthDao,
    private val rxSchedulers: NetworkModule.RxSchedulers
) : AuthMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun validateViews(emailView: ValidatableText, pwView: ValidatableText): Boolean {
        return emailView.validate() && pwView.validate()
    }

    private fun getObserver(loginView: AuthMvp.View): SingleErrorHandlingObserver<Auth> {
        return object : SingleErrorHandlingObserver<Auth>(networkUtil, disposable, loginView) {
            override fun onSuccess(t: Auth) {
                super.onSuccess(t)
                authDao.clear()
                authDao.insertAuth(t)
                loginView.hideProgress()
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

    override fun cancel() {
        disposable.clear()
    }
}