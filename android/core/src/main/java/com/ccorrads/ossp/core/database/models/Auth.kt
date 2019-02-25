package com.ccorrads.ossp.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ccorrads.ossp.core.database.models.Auth.Companion.dbTableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = dbTableName)
data class Auth(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "accessToken") @SerializedName("accessToken") val accessToken: String,
    @ColumnInfo(name = "refreshToken") @SerializedName("refreshToken") val refreshToken: String
) {

    companion object {
        const val dbTableName: String = "auth"
    }
}