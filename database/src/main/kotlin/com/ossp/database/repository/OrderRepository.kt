package com.ossp.database.repository

import com.ossp.database.model.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long> {

    fun findByUserID(userID: Long?): List<Order>?
}
