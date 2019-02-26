package com.ccorrads.ossp.core.network.interceptors

import com.ccorrads.ossp.core.BuildConfig
import okhttp3.Interceptor
import org.joda.time.DateTime
import java.util.*

class DefaultInterceptor {

    private val formattedLocale: String
        get() =
            Locale.getDefault().language.toLowerCase() + "_" + Locale.getDefault().country.toUpperCase()

    val defaultInterceptor: Interceptor
        get() = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("version", BuildConfig.VERSION_NAME + "+" + BuildConfig.VERSION_CODE + "-android")
                .header("id", BuildConfig.APPLICATION_ID)
                .header("locale", formattedLocale)
                .header("datetime", DateTime.now().toDateTimeISO().toString())
                .build()
            chain.proceed(request)
        }
}