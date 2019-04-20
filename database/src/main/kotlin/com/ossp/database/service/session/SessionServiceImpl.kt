package com.ossp.database.service.session

import com.ossp.database.error.ObjectNotFound
import com.ossp.database.model.Session
import com.ossp.database.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionServiceImpl : SessionService {

    @Autowired
    private val sessionRepository: SessionRepository? = null

    override fun listAllSessions(userId: Long?): List<Session> {
        val all = ArrayList<Session>()

        sessionRepository?.let { repository ->
            for (session in repository.findAllByUserId(userId)) {
                all.add(session)
            }
        }

        return all
    }

    override fun findById(id: Long?): Session? {
        id?.let { sessionId ->
            return this.sessionRepository?.findById(sessionId)?.get()
        } ?: run {
            throw ObjectNotFound(message = "Could not find session with id $id")
        }
    }

    override fun findSessionByUserId(userId: Long): Session? {
        return sessionRepository?.findByUserId(userId)
    }

    override fun create(session: Session): Session? {
        return this.sessionRepository?.save(session)
    }

}
