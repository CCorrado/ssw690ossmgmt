package com.ccorrads.ossp.loginregistration

import android.os.Bundle
import com.ccorrads.ossp.core.BaseActivity
import com.ccorrads.ossp.loginregistration.injection.DaggerLoginRegisterComponent
import com.ccorrads.ossp.loginregistration.injection.LoginRegisterComponent

class LoginRegisterActivity : BaseActivity() {

    var component: LoginRegisterComponent? = null

    override fun layoutResId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerLoginRegisterComponent.builder()
            .context(this)
            .build()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginRegisterFragment())
            .commit()
    }
}