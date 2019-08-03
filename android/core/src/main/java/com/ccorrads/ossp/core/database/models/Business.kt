package com.ccorrads.ossp.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ccorrads.ossp.core.database.models.Business.Companion.dbTableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = dbTableName)
data class Business(
    @PrimaryKey val dbId: Int,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
    @ColumnInfo(name = "location") @SerializedName("location") val location: String?,
    @ColumnInfo(name = "products") @SerializedName("products") val products: String?,
    @ColumnInfo(name = "createdDate") @SerializedName("createdDate") val createdDate: String
) {
    companion object {
        const val dbTableName: String = "business"
        const val dbIdColumn: String = "id"
    }

}