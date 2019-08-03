package com.ccorrads.ossp.core.network.models

data class CreateBizRequest(
    val userId: Int,
    val name: String,
    val location: String
)