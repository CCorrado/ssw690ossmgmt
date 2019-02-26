package com.ccorrads.ossp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccorrads.ossp.core.database.models.Auth

@Dao
interface AuthDao {

    /**
     * get Auth object by User ID
     */
    @Query("SELECT * FROM ${Auth.dbTableName}")
    fun getAuth(): Auth?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuth(auth: Auth)

    @Query("DELETE FROM ${Auth.dbTableName}")
    fun clear()
}