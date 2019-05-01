package com.ccorrads.ossp.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ccorrads.ossp.core.BaseApplication
import com.ccorrads.ossp.core.BaseFragment
import com.ccorrads.ossp.home.HomeActivity
import com.ccorrads.ossp.home.R
import kotlinx.android.synthetic.main.fragment_settings.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class SettingsFragment : BaseFragment(), SettingsMvp.View {

    @Inject
    lateinit var presenter: SettingsPresenter

    override val layoutResourceId: Int
        get() = R.layout.fragment_settings

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        (activity as? HomeActivity)?.component?.plus(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init(this)
        fragment_settings_logout.setOnClickListener { presenter.logout((activity?.application as? BaseApplication)?.getRouter()) }
    }

    override fun setName(name: String?) {
        settings_profile_name.text = name
    }

    override fun setEmail(email: String?) {
        settings_profile_username.text = email
    }

    override fun setCreatedDate(dateTime: DateTime?) {
        dateTime?.let {
            settings_profile_created_date.text = DateTimeFormat.fullDate().print(it)
        }
    }

    override fun setRole(role: String?) {
        settings_profile_role.text = role
    }
}