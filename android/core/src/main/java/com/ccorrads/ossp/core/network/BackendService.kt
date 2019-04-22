package com.ccorrads.ossp.core.network

import com.ccorrads.ossp.core.network.models.LoginRequest
import com.ccorrads.ossp.core.network.models.RegisterRequest
import com.ccorrads.ossp.core.network.models.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BackendService {

    @POST("users/login")
    fun loginUser(@Body request: LoginRequest): Single<UserResponse>

    @POST("users/register")
    fun registerUser(@Body request: RegisterRequest): Single<UserResponse>

    @GET("users/getUser")
    fun getCurrentUser(@Query(value = "id") userId: Int): Single<UserResponse>
}