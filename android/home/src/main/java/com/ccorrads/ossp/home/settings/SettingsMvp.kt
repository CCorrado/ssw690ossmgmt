package com.ccorrads.ossp.home.settings

import com.ccorrads.ossp.core.Mvp
import com.ccorrads.ossp.core.Router
import org.joda.time.DateTime

class SettingsMvp {

    interface View : Mvp.View {
        fun setName(name: String?)
        fun setEmail(email: String?)
        fun setCreatedDate(dateTime: DateTime?)
        fun setRole(role: String?)
    }

    interface Presenter : Mvp.Presenter {
        fun init(view: View)
        fun logout(router: Router?)
    }
}