package com.ccorrads.ossp.loginregistration.business

import android.util.Log
import com.ccorrads.ossp.core.database.dao.UserDao
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.core.network.models.BusinessResponse
import com.ccorrads.ossp.core.network.models.CreateBizRequest
import com.ccorrads.ossp.core.network.observers.SingleErrorHandlingObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateBusinessPresenter
@Inject constructor(
    private val backendService: BackendService,
    private val userDao: UserDao,
    private val networkUtil: NetworkUtil
) : CreateBusinessMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun cancel() {
        disposable.clear()
    }

    override fun searchForLocation(view: CreateBusinessMvp.View, location: String) {
        view.openMapsIntent(location)
    }

    override fun createBusinessForUser(view: CreateBusinessMvp.View) {
        view.showProgress()
        disposable.add(
            userDao.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    backendService.createBusiness(
                        CreateBizRequest(
                            userId = user.id,
                            name = view.getName(),
                            location = view.getLocation()
                        )
                    ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserver(view))
                },
                    { err ->
                        view.hideProgress()
                        Log.e("CreateBizPresenter", err.message, err)
                    })
        )
    }

    private fun getObserver(view: CreateBusinessMvp.View): SingleErrorHandlingObserver<BusinessResponse> {
        return object : SingleErrorHandlingObserver<BusinessResponse>(networkUtil, disposable, view) {
            override fun onSuccess(t: BusinessResponse) {
                super.onSuccess(t)
                view.onBusinessCreated()
            }
        }
    }
}