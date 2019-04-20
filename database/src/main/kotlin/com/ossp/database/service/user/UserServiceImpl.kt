package com.ossp.database.service.user

import com.ossp.database.error.ObjectNotFound
import com.ossp.database.model.User
import com.ossp.database.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl : UserService {

    @Autowired
    private val userRepository: UserRepository? = null

    override fun list(): List<User> {
        val all = ArrayList<User>()

        userRepository?.let { repository ->
            for (user in repository.findAll()) {
                all.add(user)
            }
        }

        return all
    }

    override fun findById(id: Long?): User? {
        id?.let { userId ->
            return this.userRepository?.findById(userId)?.get()
        } ?: run {
            throw ObjectNotFound(message = "Could not find user with id $id")
        }
    }

    override fun findByUsername(username: String): User? {
        return this.userRepository?.findByUsername(username)
    }

    override fun create(user: User): User? {
        return this.userRepository?.save(user)
    }
}