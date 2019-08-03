package com.ccorrads.ossp.loginregistration.business

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CreateBusinessModule {

    @Provides
    @Singleton
    fun presenter(presenter: CreateBusinessPresenter): CreateBusinessMvp.Presenter {
        return presenter
    }

}