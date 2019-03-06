package com.ccorrads.ossp

import android.app.Application
import com.facebook.stetho.Stetho
import net.danlew.android.joda.JodaTimeAndroid

class OSSPApp : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)

        Stetho.initializeWithDefaults(this)

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                    Stetho.defaultDumperPluginsProvider(this)
                )
                .enableWebKitInspector(
                    Stetho.defaultInspectorModulesProvider(this)
                )
                .build()
        )
    }
}