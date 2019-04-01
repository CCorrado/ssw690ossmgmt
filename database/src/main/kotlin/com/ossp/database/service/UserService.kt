package com.ossp.database.service

import com.ossp.database.model.User

interface UserService {

    fun list(): List<User>

    fun findById(id: Long?): User?

    fun findByUsername(username: String): User?

    fun create(user: User): User?
}