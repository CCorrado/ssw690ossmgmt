package com.ccorrads.ossp.core.network.models

import org.joda.time.DateTime

data class UserResponse(
    val username: String,
    val name: String,
    val role: String,
    val userId: Int,
    val sessionId: Int,
    val userCreatedDate: DateTime,
    val sessionCreatedDate: DateTime,
    val refreshToken: String,
    val accessToken: String,
    val expiresIn: Long,
    val tokenType: String
)