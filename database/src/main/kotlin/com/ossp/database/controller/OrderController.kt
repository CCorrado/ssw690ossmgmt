package com.ossp.database.controller

import com.ossp.database.model.Order
import com.ossp.database.model.OrderStatus
import com.ossp.database.service.orders.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.server.ResponseStatusException

@Controller
class OrderController {

    @Autowired
    private val orderService: OrderService? = null

    @RequestMapping(path = ["/orders"], method = [RequestMethod.GET])
    fun list(): Iterable<Order>? {
        return orderService?.all()
    }

    @RequestMapping(path = ["/orders/create"], method = [RequestMethod.POST])
    fun createOrder(@RequestBody order: Order): Order? {
        return orderService?.create(order)
    }

    @RequestMapping(path = ["/orders-delete/{orderID}"], method = [RequestMethod.POST])
    fun deleteOrder(@PathVariable("orderID") orderID: Long?): Boolean? {
        orderService?.delete(orderService.findById(orderID))
        return true
    }

    @RequestMapping(path = ["/orders/status/{orderID}"])
    fun getByOrderID(@PathVariable("orderID") orderID: Long?): OrderStatus {
        val order = orderService?.findById(orderID)

        if (order == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")
        } else {
            val orderStatus = OrderStatus()
            orderStatus.userid = order.userID
            orderStatus.orderid = order.orderID
            orderStatus.status = order.status

            return orderStatus
        }
    }

    @RequestMapping("/orders/{userID}")
    fun list(@PathVariable("userID") userID: Long?): List<Order>? {
        try {
            return orderService?.findByUserID(userID)
        } catch (exp: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")
        }

    }
}