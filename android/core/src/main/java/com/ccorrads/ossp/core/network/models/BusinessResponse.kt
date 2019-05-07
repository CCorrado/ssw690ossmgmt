package com.ccorrads.ossp.core.network.models

import org.joda.time.DateTime

data class BusinessResponse(
    val name: String,
    val userId: Int,
    val location: String,
    val createdDate: DateTime,
    val products: String
)