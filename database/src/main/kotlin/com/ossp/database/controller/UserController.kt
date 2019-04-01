package com.ossp.database.controller

import com.ossp.database.model.AuthInfo
import com.ossp.database.model.User
import com.ossp.database.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class UserController {

    @Autowired
    private val userService: UserService? = null

    @RequestMapping(path = ["/users"])
    fun list(): List<User>? {
        return userService?.list()
    }

    @RequestMapping(path = ["/users/{id}"])
    operator fun get(@PathVariable("id") id: Long?): User? {
        userService?.findById(id)?.let {
            return it
        } ?: run {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id not found")
        }
    }

    @RequestMapping(path = ["/users/by-username/{username}"])
    fun getByUsername(@PathVariable("username") username: String): AuthInfo {
        val user = userService?.findByUsername(username)

        user?.let {
            return AuthInfo(it.userName, it.password)
        } ?: run {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usr with username $username not found")
        }
    }

    @RequestMapping(path = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody user: User): User? {
        return userService?.create(user)
    }
}