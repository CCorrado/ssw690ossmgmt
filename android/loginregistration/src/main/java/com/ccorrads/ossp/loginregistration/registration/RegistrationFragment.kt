package com.ccorrads.ossp.loginregistration.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ccorrads.ossp.core.BaseFragment
import com.ccorrads.ossp.home.HomeActivity
import com.ccorrads.ossp.loginregistration.LoginRegisterActivity
import com.ccorrads.ossp.loginregistration.R
import com.ccorrads.ossp.loginregistration.registration.injection.RegisterPresenter
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment(), RegisterMvp.View {

    @Inject
    lateinit var presenter: RegisterPresenter

    override val layoutResourceId: Int
        get() = R.layout.fragment_registration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? LoginRegisterActivity)?.component?.plus(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_name_edit.setErrorTextLayout(register_name)
        register_email_edit.setErrorTextLayout(register_email)
        register_password_edit.setErrorTextLayout(register_password)
        register_password_edit_repeat.setErrorTextLayout(register_password_repeat)
        register_email_edit.setErrorString(getString(R.string.error_invalid_email))
        register_password_edit.setErrorString(getString(R.string.error_invalid_password))
        register_password_edit_repeat.setErrorString(getString(R.string.error_invalid_password))
        register_name_edit.setErrorString(getString(R.string.error_field_required))

        fragment_register_button.setOnClickListener {
            if (presenter.validateViews(
                    register_email_edit,
                    register_password_edit,
                    register_password_edit_repeat,
                    register_name_edit
                )
            ) {
                showProgress()
                presenter.registerUser(
                    registerView = this,
                    fullName = register_name_edit.text?.toString(),
                    username = register_email_edit.text?.toString(),
                    pw = register_password_edit.text?.toString()
                )
            }
        }
    }

    override fun onAuthenticated() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }
}