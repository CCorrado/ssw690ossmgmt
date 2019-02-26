package com.ccorrads.ossp.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ccorrads.ossp.core.database.models.User.Companion.dbTableName
import com.google.gson.annotations.SerializedName


@Entity(tableName = dbTableName)
data class User(
    @PrimaryKey val dbId: Int,
    @ColumnInfo(name = "type") @SerializedName("type") val type: String,
    @ColumnInfo(name = "id") @SerializedName("id") val id: String,
    @ColumnInfo(name = "role") @SerializedName("role") val role: UserRole,
    @ColumnInfo(name = "fullName") @SerializedName("fullName") val fullName: String?,
    @ColumnInfo(name = "age") @SerializedName("age") val age: String?
) {
    companion object {
        const val dbTableName: String = "user"
    }

    enum class UserRole {
        Consumer,
        Business,
        Admin
    }
}