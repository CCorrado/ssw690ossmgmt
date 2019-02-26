package com.ccorrads.ossp.core

interface ErrorView {

    fun onServerError(title: String, error: String)

    fun onServerError(error: String?)

    fun onNetworkError()

    fun onUnknownError()

}