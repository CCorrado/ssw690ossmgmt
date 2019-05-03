package com.ossp.database.service.orders

import com.ossp.database.model.Order

interface OrderService {

    fun create(order: Order): Order?

    fun delete(order: Order?)

    fun findById(id: Long?): Order?

    fun findByUserID(userID: Long?): List<Order>?

    fun all(): Iterable<Order>?
}