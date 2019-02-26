package com.ccorrads.ossp.loginregistration.views

import android.text.TextUtils
import android.util.Patterns

object ValidatorUtil {

    fun isValidEmail(email: String?): Boolean {
        val emailRegex = Patterns.EMAIL_ADDRESS.toRegex()
        return !TextUtils.isEmpty(email) && emailRegex.matches(email!!)
    }

    fun isValidPassword(pw: String?): Boolean {
        return !TextUtils.isEmpty(pw)
    }
}