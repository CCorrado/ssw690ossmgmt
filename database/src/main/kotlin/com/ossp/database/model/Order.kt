package com.ossp.database.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "Order")
class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "orderID")
    var orderID: Long = 0

    @Column(name = "status")
    var status: String? = null

    @Column(name = "createdDate")
    var createDate: Date = Date()

    @Column(name = "destination")
    var destination: String? = null

    @Column(name = "userID")
    var userID: Long = 0

    @Column(name = "businessID")
    var businessID: Long = 0

    @Column(name = "goods")
    var goods: String? = null
}