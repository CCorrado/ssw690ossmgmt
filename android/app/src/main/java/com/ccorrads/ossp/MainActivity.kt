package com.ccorrads.ossp

import android.content.Intent
import android.os.Bundle
import com.ccorrads.ossp.core.BaseActivity
import com.ccorrads.ossp.loginregistration.LoginRegisterActivity

class MainActivity : BaseActivity() {

    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LoginRegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}