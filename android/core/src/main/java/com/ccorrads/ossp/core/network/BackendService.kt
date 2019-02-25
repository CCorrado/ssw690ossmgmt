package com.ccorrads.ossp.core.network

import com.ccorrads.ossp.core.database.models.Auth
import com.ccorrads.ossp.core.network.models.LoginRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BackendService {

    @POST("login")
    fun loginUser(@Body request: LoginRequest): Single<Auth>
}