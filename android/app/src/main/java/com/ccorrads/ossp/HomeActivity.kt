package com.ccorrads.ossp

import android.content.Intent
import android.os.Bundle
import com.ccorrads.ossp.core.BaseActivity
import com.ccorrads.ossp.loginregistration.LoginRegisterActivity

class HomeActivity : BaseActivity() {

    override fun layoutResId(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LoginRegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}