package com.ccorrads.ossp.core.database

import androidx.room.TypeConverter
import com.ccorrads.ossp.core.database.models.User
import org.joda.time.DateTime

class Converters {

    @TypeConverter
    fun fromTimestamp(value: String?): DateTime? {
        return if (value == null) null else DateTime(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: DateTime?): String? {
        return date?.toDateTimeISO()?.toString()
    }

    @TypeConverter
    fun fromRole(value: String?): User.UserRole {
        return if (value == null) User.UserRole.Consumer else
            when (value) {
                "Consumer" -> return User.UserRole.Consumer
                "Admin" -> return User.UserRole.Admin
                "Business" -> return User.UserRole.Business
                else -> User.UserRole.Consumer
            }
    }

    @TypeConverter
    fun roleTostring(role: User.UserRole?): String? {
        return role?.toString()
    }
}