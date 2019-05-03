package com.ossp.database.service.orders

import com.ossp.database.error.ObjectNotFound
import com.ossp.database.model.Order
import com.ossp.database.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OrderServiceImpl : OrderService {

    @Autowired
    private val orderRepository: OrderRepository? = null

    override fun create(order: Order): Order? {
        return this.orderRepository?.save(order)
    }

    override fun delete(order: Order?) {
        order?.let {
            this.orderRepository?.delete(it)
        }
    }

    override fun findById(id: Long?): Order? {
        id?.let {
            return this.orderRepository?.findById(it)?.get()
        } ?: run {
            throw ObjectNotFound(message = "Order Not Found")
        }
    }

    override fun findByUserID(userID: Long?): List<Order> {
        val all = ArrayList<Order>()

        orderRepository?.findByUserID(userID)?.let { orders ->
            for (order in orders) {
                all.add(order)
            }
        }

        return all
    }

    override fun all(): Iterable<Order>? {
        return this.orderRepository?.findAll()
    }

}
