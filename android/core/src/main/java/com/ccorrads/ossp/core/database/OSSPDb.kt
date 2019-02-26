package com.ccorrads.ossp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import com.ccorrads.ossp.core.database.models.Auth
import com.ccorrads.ossp.core.database.models.User

@Database(
    entities = [Auth::class, User::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class OSSPDb : RoomDatabase() {

    abstract fun getAuthDao(): AuthDao

    abstract fun getUserDao(): UserDao

}