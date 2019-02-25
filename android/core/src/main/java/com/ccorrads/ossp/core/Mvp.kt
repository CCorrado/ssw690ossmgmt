package com.ccorrads.ossp.core

class Mvp {

    interface View : ErrorView {

        /**
         * shows progress indicator on UI
         */
        fun showProgress()

        /**
         * hides progress indicator on UI
         */
        fun hideProgress()

        /**
         * shows notification to user
         */
        fun showMessage(message: String)
    }

    interface Presenter {

        /**
         * cancels the ongoing background operations (such as database or REST API communication)
         * when user exists screen
         */
        fun cancel()
    }

}