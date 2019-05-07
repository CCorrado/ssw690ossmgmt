package com.ccorrads.ossp.loginregistration.business

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.ccorrads.ossp.core.BaseActivity
import com.ccorrads.ossp.loginregistration.R


class CreateBusinessActivity : BaseActivity(), CreateBusinessMvp.View {

    override fun layoutResId(): Int {
        return R.layout.activity_create_business
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun openMapsIntent(location: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }
}