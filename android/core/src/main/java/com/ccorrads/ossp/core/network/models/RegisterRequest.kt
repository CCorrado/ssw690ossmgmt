package com.ccorrads.ossp.core.network.models

import org.joda.time.DateTime

data class RegisterRequest(
        val username: String?,
        val password: String?,
        val createDate: DateTime,
        val role: String?,
        val name: String?
)