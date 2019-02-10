package com.ccorrads.ossp

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class OSSPApp : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}