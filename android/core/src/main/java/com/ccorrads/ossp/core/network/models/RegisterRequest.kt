package com.ccorrads.ossp.core.network.models

data class RegisterRequest(
    val username: String?,
    val password: String?,
    val fullName: String?
)