package com.ossp.database.model

import com.google.gson.annotations.SerializedName
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "Session")
class Session {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "sessionId")
    var sessionId: Long? = 0

    @Column(name = "userId")
    var userId: Long? = 0

    @Column(name = "createdDate")
    var createdDate: Date? = Date()

    @Column(name = "refreshToken")
    @SerializedName("refresh_token")
    var refreshToken: String? = null

    @Column(name = "accessToken")
    @SerializedName("access_token")
    var accessToken: String? = null

    @Column(name = "expiresIn")
    @SerializedName("expires_in")
    var expiresIn: Long? = 0

    @Column(name = "tokenType")
    @SerializedName("token_type")
    var tokenType: String? = null
}
