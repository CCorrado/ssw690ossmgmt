package com.ccorrads.ossp.core.network.models

import org.joda.time.DateTime

data class RegisterRequest(
        val userName: String?,
        val password: String?,
        val createDate: DateTime,
        val role: String?,
        val name: String?
)