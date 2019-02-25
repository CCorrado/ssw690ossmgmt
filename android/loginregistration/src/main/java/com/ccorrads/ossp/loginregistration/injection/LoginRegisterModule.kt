package com.ccorrads.ossp.loginregistration.injection

import com.ccorrads.ossp.loginregistration.AuthMvp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginRegisterModule {

    @Provides
    @Singleton
    fun presenter(presenter: LoginRegisterPresenter): AuthMvp.Presenter {
        return presenter
    }
}