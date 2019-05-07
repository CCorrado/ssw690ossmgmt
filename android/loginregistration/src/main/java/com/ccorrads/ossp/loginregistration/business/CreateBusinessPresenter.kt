package com.ccorrads.ossp.loginregistration.business

import io.reactivex.disposables.CompositeDisposable

class CreateBusinessPresenter : CreateBusinessMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun cancel() {
        disposable.clear()
    }
}