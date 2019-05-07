package com.ccorrads.ossp.core.network.models

data class ProductResponse(
    val businessId: Int,
    val name: String,
    val price: String,
    val quantity: Int
)