package com.ccorrads.ossp.home.settings

import com.ccorrads.ossp.core.Mvp
import com.ccorrads.ossp.core.Router

class SettingsMvp {

    interface View : Mvp.View

    interface Presenter : Mvp.Presenter {
        fun logout(router: Router?)
    }
}