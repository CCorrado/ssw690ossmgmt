package com.ccorrads.ossp.loginregistration.business

import com.ccorrads.ossp.core.Mvp

class CreateBusinessMvp {

    interface View : Mvp.View{
        fun openMapsIntent(location: String)
    }

    interface Presenter : Mvp.Presenter
}