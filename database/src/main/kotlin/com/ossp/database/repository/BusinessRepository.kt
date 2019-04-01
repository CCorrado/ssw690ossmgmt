package com.ossp.database.repository

import com.ossp.database.model.Business
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BusinessRepository : CrudRepository<Business, Long>