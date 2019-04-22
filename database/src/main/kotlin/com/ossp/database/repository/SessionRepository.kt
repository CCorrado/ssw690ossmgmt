package com.ossp.database.repository

import com.ossp.database.model.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : CrudRepository<Session, Long> {

    fun findByUserId(userId: Long?): Session?

    fun findAllByUserId(userId: Long?): List<Session>
}