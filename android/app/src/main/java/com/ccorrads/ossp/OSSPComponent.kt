package com.ccorrads.ossp

import android.content.Context
import com.ccorrads.ossp.core.Router
import com.ccorrads.ossp.core.injection.DatabaseModule
import com.ccorrads.ossp.core.injection.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, NetworkModule::class])
@Singleton
interface OSSPComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): OSSPComponent
    }

    fun getRouter(): Router?

}