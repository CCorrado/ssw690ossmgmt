package com.ossp.database.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "Business")
class Business {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "businessID")
    var businessID: Long = 0

    @Column(name = "businessName")
    var businessName: String? = null

    @Column(name = "createdDate")
    var createDate: Date? = null
}