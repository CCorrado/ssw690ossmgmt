package com.ccorrads.ossp.core.network

import com.ccorrads.ossp.core.network.models.*
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

    @POST("business/create")
    fun createBusiness(@Body request: CreateBizRequest): Single<BusinessResponse>

    @POST("business/products/create")
    fun createProductForBusiness(@Body request: ProductRequest): Single<ProductResponse>

    @GET("business/getAll")
    fun getBusinesses(): Single<List<BusinessResponse>>

    @GET("users/getUser")
    fun getCurrentUser(@Query(value = "id") userId: Int): Single<UserResponse>
}