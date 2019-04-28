package com.ccorrads.ossp.core

import android.app.Application

abstract class BaseApplication : Application() {

    abstract fun getRouter(): Router?

}