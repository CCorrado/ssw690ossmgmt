package com.ccorrads.ossp.core

import android.content.Context
import android.content.Intent

class Router {

    private var registrationActivity: Class<out BaseActivity>? = null

    fun setRegistrationActivity(registerActivity: Class<out BaseActivity>) {
        registrationActivity = registerActivity
    }

    fun logout(context: Context) {
        registrationActivity?.newInstance()?.let { activity ->
            val intent = Intent(context, activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}