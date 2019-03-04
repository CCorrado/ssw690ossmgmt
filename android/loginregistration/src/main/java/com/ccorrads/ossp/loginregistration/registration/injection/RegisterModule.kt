package com.ccorrads.ossp.loginregistration.registration.injection

import com.ccorrads.ossp.loginregistration.registration.RegisterMvp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RegisterModule {

    @Provides
    @Singleton
    fun presenter(presenter: RegisterPresenter): RegisterMvp.Presenter {
        return presenter
    }
}