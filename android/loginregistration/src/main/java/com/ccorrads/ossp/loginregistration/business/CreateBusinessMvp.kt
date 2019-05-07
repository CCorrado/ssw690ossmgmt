package com.ccorrads.ossp.loginregistration.business

import com.ccorrads.ossp.core.Mvp

class CreateBusinessMvp {

    interface View : Mvp.View {
        fun openMapsIntent(location: String)

        fun getName(): String

        fun getLocation(): String

        fun onBusinessCreated()
    }

    interface Presenter : Mvp.Presenter {
        fun searchForLocation(view: View, location: String)

        fun createBusinessForUser(view: View)
    }
}