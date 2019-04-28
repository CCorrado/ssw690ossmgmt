package com.ccorrads.ossp.home

import android.content.Context
import com.ccorrads.ossp.core.injection.DatabaseModule
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.home.settings.SettingsFragment
import com.ccorrads.ossp.home.settings.SettingsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, NetworkModule::class, SettingsModule::class])
@Singleton
interface HomeComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): HomeComponent
    }

    fun inject(activity: HomeActivity)

    fun plus(settingsFragment: SettingsFragment)

}