package com.ossp.database.service.session

import com.ossp.database.model.Session

interface SessionService {

    fun listAllSessions(userId: Long?): List<Session>

    fun findById(id: Long?): Session?

    fun findSessionByUserId(userId: Long): Session?

    fun create(session: Session): Session?
}