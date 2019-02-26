package com.ccorrads.ossp.core.injection

import android.content.Context
import com.ccorrads.ossp.core.R
import com.ccorrads.ossp.core.database.OSSPDb
import com.ccorrads.ossp.core.injection.adapters.DateTimeTypeAdapter
import com.ccorrads.ossp.core.injection.adapters.LocalDateTypeAdapter
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.core.network.interceptors.AuthInterceptor
import com.ccorrads.ossp.core.network.interceptors.DefaultInterceptor
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import org.joda.time.LocalDate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(DateTime::class.java, DateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter().nullSafe())
            .create()
    }

    @Singleton
    @Provides
    fun providesInterceptor(osspDb: OSSPDb): AuthInterceptor {
        return AuthInterceptor(osspDb)
    }

    @Singleton
    @Provides
    fun providesOkClient(context: Context, interceptor: AuthInterceptor): OkHttpClient {

        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(DefaultInterceptor().defaultInterceptor)
        okHttpBuilder.addInterceptor(interceptor)
        //Handle cookies
        okHttpBuilder.cookieJar(cookieJar)

        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun providesBackendService(context: Context, okHttpClient: OkHttpClient, gson: Gson): BackendService {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(BackendService::class.java)
    }

    @Singleton
    @Provides
    fun providesNetworkUtil(context: Context, gson: Gson): NetworkUtil {
        return NetworkUtil(context, gson)
    }

    data class RxSchedulers(
        val database: Scheduler,
        val disk: Scheduler,
        val network: Scheduler,
        val main: Scheduler
    )

    @Singleton
    @Provides
    fun provideRxSchedulers() = RxSchedulers(
        database = Schedulers.single(),
        disk = Schedulers.io(),
        network = Schedulers.io(),
        main = AndroidSchedulers.mainThread()
    )
}