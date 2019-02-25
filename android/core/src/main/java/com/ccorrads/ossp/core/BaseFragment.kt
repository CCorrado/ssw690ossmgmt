package com.ccorrads.ossp.core

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.ccorrads.ossp.core.views.WaitDialogFragment

abstract class BaseFragment : Fragment(), Mvp.View {

    protected abstract val layoutResourceId: Int

    private var spinner: WaitDialogFragment? = null

    protected val baseActivity: BaseActivity
        get() = activity as BaseActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun showProgress() {
        if (spinner == null) {
            spinner = WaitDialogFragment.newInstance(false)
        }

        spinner?.let { dialog ->
            fragmentManager?.let { fragManager ->
                if (!dialog.isAdded) {
                    dialog.show(fragManager, "WaitDialogFragment")
                }
            }
        }
    }

    override fun hideProgress() {
        spinner?.let {
            if (it.isAdded) {
                it.dismissAllowingStateLoss()
            }
        }
    }

    override fun showMessage(message: String) {
        showMessage(message, android.R.string.ok, null)
    }

    private fun showMessage() {
        showMessage(getString(R.string.unknown_error))
    }

    private fun showMessage(message: String?, @StringRes button: Int, callback: DialogInterface.OnClickListener?) {
        AlertDialog.Builder(activity, R.style.AlertDialogTheme)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(button) { dialog, i ->
                dialog.dismiss()
                callback?.onClick(dialog, i)
            }
            .show()
    }

    override fun onServerError(title: String, error: String) {
        showMessage(error)
    }

    override fun onServerError(error: String?) {
        if (error != null) {
            showMessage(error)
        }
    }

    override fun onNetworkError() {
        showMessage()
    }

    override fun onUnknownError() {
        showMessage()
    }
}