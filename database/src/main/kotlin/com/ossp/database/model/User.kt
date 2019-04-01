package com.ossp.database.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "User")
class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userID")
    var userID: Long = 0

    @Column(name = "role")
    var role: String? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "userName")
    var userName: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "createdDate")
    var createDate: Date = Date()
}
