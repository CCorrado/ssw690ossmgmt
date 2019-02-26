package com.ccorrads.ossp.core.views

import com.ccorrads.ossp.core.R

class WaitDialogFragment : BaseFullscreenDialogFragment() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_dialog_wait

    companion object {

        fun newInstance(cancelable: Boolean): WaitDialogFragment {
            val fragment = WaitDialogFragment()
            fragment.isCancelable = cancelable
            return fragment
        }
    }
}