package com.ccorrads.ossp.core.database.dao

import androidx.room.*
import com.ccorrads.ossp.core.database.models.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM ${User.dbTableName}")
    fun getAllUsers(): Flowable<List<User>>

    @Query("SELECT * FROM ${User.dbTableName} WHERE ${"id"} = :id")
    fun getUserForId(id: Int): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM ${User.dbTableName}")
    fun clear()
}