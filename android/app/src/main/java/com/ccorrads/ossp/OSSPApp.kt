package com.ccorrads.ossp

import com.ccorrads.ossp.core.BaseApplication
import com.ccorrads.ossp.core.Router
import com.ccorrads.ossp.loginregistration.LoginRegisterActivity
import com.ccorrads.ossp.loginregistration.business.CreateBusinessActivity
import com.facebook.stetho.Stetho
import net.danlew.android.joda.JodaTimeAndroid

class OSSPApp : BaseApplication() {

    private var component: OSSPComponent? = null

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

        buildComponent()

        getRouter()?.setRegistrationActivity(LoginRegisterActivity::class.java)
        getRouter()?.setCreateBusinessActivity(CreateBusinessActivity::class.java)
    }

    override fun getRouter(): Router? {
        return component?.getRouter()
    }

    private fun buildComponent() {
        component = DaggerOSSPComponent.builder().context(this).build()
    }
}