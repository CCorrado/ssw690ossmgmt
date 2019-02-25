package com.ccorrads.ossp.loginregistration.views

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import com.ccorrads.ossp.core.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.jetbrains.annotations.Nullable

open class ValidatableText : TextInputEditText {

    @Nullable
    private var errorTextLayout: TextInputLayout? = null

    private var errorString = resources.getString(R.string.default_error)

    private var isValid: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        configureEditorActionListener()
        configureFocusChangedListener()
        configureTextWatcher()
    }

    fun validate(): Boolean {
        isValid = textIsValid()

        if (!isValid) {
            errorTextLayout?.isErrorEnabled = true
            errorTextLayout?.error = errorString
        }
        return isValid
    }

    /**
     * Optional Method to set an error text layout to propogate errors to.
     *
     * @param errorTextLayout -- [TextInputLayout] to propogate errors to.
     */
    fun setErrorTextLayout(@Nullable errorTextLayout: TextInputLayout) {
        this.errorTextLayout = errorTextLayout
    }

    /**
     * Optionally set a custom error state string based on requirements
     *
     * @param errorString -- Error String to show.
     */
    fun setErrorString(errorString: String) {
        this.errorString = errorString
    }

    /**
     * Default validation for all text views is whether or not the view is empty.
     * (Default to required for all text views)
     *
     * @return -- True if the text is currently valid.
     */
    open fun textIsValid(): Boolean {
        return !TextUtils.isEmpty(text.toString())
    }

    /**
     * Configure a Default Text Watcher to wipe away errors and validate as the user types.
     */
    private fun configureTextWatcher() {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //no-op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //update state
                isValid = textIsValid()

                if (isValid && errorTextLayout != null) {
                    errorTextLayout?.error = null
                    errorTextLayout?.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {
                //no-op
            }
        })
    }

    /**
     * Configure a Default Focus Changed Listener to validate as the use focuses on different fields.
     */
    private fun configureFocusChangedListener() {
        this.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                updateTextLayout()
            }
        }
    }

    /**
     * Configure a Default Editor Action Listener to validate as the user presses NEXT on the keyboard
     */
    private fun configureEditorActionListener() {
        this.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                updateTextLayout()
            }
            !isValid
        }
    }

    private fun updateTextLayout() {
        //update state
        isValid = textIsValid()
        if (!isValid) {
            errorTextLayout?.isErrorEnabled = true
            errorTextLayout?.error = errorString
        } else {
            errorTextLayout?.error = null
            errorTextLayout?.isErrorEnabled = false
        }
    }
}