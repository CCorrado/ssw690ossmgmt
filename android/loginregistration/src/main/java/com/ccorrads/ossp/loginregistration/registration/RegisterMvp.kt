package com.ccorrads.ossp.loginregistration.registration

import com.ccorrads.ossp.core.Mvp
import com.ccorrads.ossp.loginregistration.views.ValidatableText

class RegisterMvp {
    interface View : Mvp.View {
        fun onAuthenticated()
    }

    interface Presenter : Mvp.Presenter {

        fun validateViews(
            emailView: ValidatableText, pwView: ValidatableText,
            repeatPwView: ValidatableText, nameView: ValidatableText
        ): Boolean

        fun registerUser(registerView: RegisterMvp.View, username: String?, pw: String?, fullName: String?)
    }
}