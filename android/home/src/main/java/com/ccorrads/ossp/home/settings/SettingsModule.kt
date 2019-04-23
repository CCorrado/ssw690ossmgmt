package com.ccorrads.ossp.home.settings

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingsModule {

    @Provides
    @Singleton
    fun presenter(presenter: SettingsPresenter): SettingsMvp.Presenter {
        return presenter
    }
}