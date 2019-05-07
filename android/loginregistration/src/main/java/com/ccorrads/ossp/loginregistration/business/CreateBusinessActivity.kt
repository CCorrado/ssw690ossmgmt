package com.ccorrads.ossp.loginregistration.business

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ccorrads.ossp.core.BaseActivity
import com.ccorrads.ossp.loginregistration.R
import com.ccorrads.ossp.loginregistration.views.ValidatableText
import javax.inject.Inject


class CreateBusinessActivity : BaseActivity(), CreateBusinessMvp.View {

    var component: CreateBusinessComponent? = null

    @Inject
    lateinit var presenter: CreateBusinessMvp.Presenter

    override fun layoutResId(): Int {
        return R.layout.activity_create_business
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerCreateBusinessComponent.builder()
            .context(this)
            .build()
        component?.inject(this)

        findViewById<TextView>(R.id.register_business_location_icon)?.setOnClickListener {
            presenter.searchForLocation(this, getLocation())
        }

        findViewById<Button>(R.id.register_business_button)?.setOnClickListener {
            presenter.createBusinessForUser(this)
        }
    }

    override fun getName(): String {
        var name = ""
        findViewById<ValidatableText>(R.id.register_business_name_edit)?.text?.toString()?.let {
            name = it
        }
        return name
    }

    override fun getLocation(): String {
        var location = ""
        findViewById<ValidatableText>(R.id.register_business_location_edit)?.text?.toString()?.let {
            location = it
        }
        return location
    }

    override fun onBusinessCreated() {
        this.finish()
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