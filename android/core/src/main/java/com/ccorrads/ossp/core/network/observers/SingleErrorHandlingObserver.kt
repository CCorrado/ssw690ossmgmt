package com.ccorrads.ossp.core.network.observers

import com.ccorrads.ossp.core.Mvp
import com.ccorrads.ossp.core.network.NetworkUtil
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class SingleErrorHandlingObserver<T>(
    private val networkUtil: NetworkUtil,
    private val compositeDisposable: CompositeDisposable?,
    private val errorView: Mvp.View
) : SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        compositeDisposable?.add(d)
    }

    override fun onSuccess(t: T) {
        //no-op
    }

    override fun onError(t: Throwable) {
        errorView.hideProgress()
        networkUtil.defaultHandleException(t, errorView)
    }
}