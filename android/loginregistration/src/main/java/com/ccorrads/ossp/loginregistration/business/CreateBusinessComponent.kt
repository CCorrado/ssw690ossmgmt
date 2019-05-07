package com.ccorrads.ossp.loginregistration.business

import android.content.Context
import com.ccorrads.ossp.core.injection.DatabaseModule
import com.ccorrads.ossp.core.injection.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, CreateBusinessModule::class])
interface CreateBusinessComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): CreateBusinessComponent
    }

    fun inject(activity: CreateBusinessActivity)

    fun getPresenter(): CreateBusinessMvp.Presenter
}