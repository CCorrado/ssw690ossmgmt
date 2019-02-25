package com.ccorrads.ossp.loginregistration

import android.os.Bundle
import android.view.View
import com.ccorrads.ossp.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_login_register.*
import javax.inject.Inject

class LoginRegisterFragment : BaseFragment(), AuthMvp.View {

    @Inject
    lateinit var presenter: AuthMvp.Presenter

    override val layoutResourceId: Int
        get() {
            return R.layout.fragment_login_register
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? LoginRegisterActivity)?.component?.plus(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_email_edit.setErrorTextLayout(login_email)
        login_password_edit.setErrorTextLayout(login_password)
        login_email_edit.setErrorString(getString(R.string.error_invalid_email))
        login_password_edit.setErrorString(getString(R.string.error_invalid_password))

        fragment_login_button.setOnClickListener {
            if (presenter.validateViews(login_email_edit, login_password_edit)) {
                showProgress()
                presenter.onClickLogin(
                    this,
                    login_email_edit.text?.toString(),
                    login_password_edit.text?.toString()
                )
            }
        }
    }
}