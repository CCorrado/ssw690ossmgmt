package com.ccorrads.ossp.core

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.ccorrads.ossp.core.views.WaitDialogFragment

abstract class BaseActivity : AppCompatActivity(), Mvp.View {

    private var spinner: WaitDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
    }

    abstract fun layoutResId(): Int

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

    override fun showMessage(message: String) {
        showMessage(message, android.R.string.ok, null)
    }

    private fun showMessage() {
        showMessage(getString(R.string.unknown_error))
    }

    private fun showMessage(message: String?, @StringRes button: Int, callback: DialogInterface.OnClickListener?) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(button) { dialog, i ->
                dialog.dismiss()
                callback?.onClick(dialog, i)
            }
            .show()
    }

    override fun showProgress() {
        if (spinner == null) {
            spinner = WaitDialogFragment()
        }

        spinner?.let { dialog ->
            if (!dialog.isAdded) {
                spinner?.show(supportFragmentManager, "WaitDialogFragment")
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
}