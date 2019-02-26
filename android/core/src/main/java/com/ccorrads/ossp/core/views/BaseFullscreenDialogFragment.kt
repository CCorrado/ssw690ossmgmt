package com.ccorrads.ossp.core.views

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment

abstract class BaseFullscreenDialogFragment : DialogFragment() {

    protected abstract val layoutResourceId: Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val view = activity?.layoutInflater?.inflate(layoutResourceId, null)
        dialog.setContentView(view)

        // needs to be called after you set the content view
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (view != null) {
            onViewCreated(view, savedInstanceState)
        }
        return dialog
    }

}