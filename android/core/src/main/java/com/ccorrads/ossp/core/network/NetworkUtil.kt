package com.ccorrads.ossp.core.network

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import com.ccorrads.ossp.core.ErrorView
import com.ccorrads.ossp.core.R
import com.ccorrads.ossp.core.network.models.ResponseError
import com.google.gson.Gson
import io.reactivex.annotations.Nullable
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkUtil(private val context: Context, private val gson: Gson) {

    private val TAG = "NetworkUtil"

    @Nullable
    private fun getErrorObject(errorJson: String?): ResponseError? {
        return try {
            gson.fromJson(errorJson, ResponseError::class.java)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            null
        }
    }

    fun defaultHandleException(t: Throwable, error: ErrorView) {
        Log.e(TAG, t.message, t)
        error.onServerError(getErrorMessage(context, t))
    }

    @NonNull
    private fun getErrorMessage(context: Context?, error: Throwable): String? {
        if (error is SocketTimeoutException || error is UnknownHostException) {
            return context?.getString(R.string.error_network_issue)
        }

        if (error is HttpException) {
            val message = parseResponseError(error.response().errorBody())

            if (message != null) {
                return message
            }
        }

        return context?.getString(R.string.unknown_error)
    }

    @Nullable
    private fun parseResponseError(responseBody: ResponseBody?): String? {
        return try {
            val responseError = getErrorObject(responseBody?.string())
            responseError?.message
        } catch (err: Exception) {
            Log.e(TAG, err.message, err)
            null
        }
    }
}