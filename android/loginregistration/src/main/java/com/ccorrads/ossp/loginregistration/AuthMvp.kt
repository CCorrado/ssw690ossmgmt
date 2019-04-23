package com.ccorrads.ossp.loginregistration

import com.ccorrads.ossp.core.Mvp
import com.ccorrads.ossp.loginregistration.views.ValidatableText

class AuthMvp {

    interface View : Mvp.View {
        fun navigateToRegistration()

        fun onAuthenticated()
    }

    interface Presenter : Mvp.Presenter {

        fun validateViews(emailView: ValidatableText, pwView: ValidatableText): Boolean

        fun onClickLogin(loginView: AuthMvp.View, user: String?, pw: String?)

        fun onClickRegister(loginView: AuthMvp.View)
    }
}