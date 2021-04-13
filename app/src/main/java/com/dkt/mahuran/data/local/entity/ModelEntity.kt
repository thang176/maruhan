package com.dkt.mahuran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ModelEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "model_code") val modelCode: String?,
    @ColumnInfo(name = "official_number") val officialNumber: String?,
    @ColumnInfo(name = "model_name") val modelName: String?,
    @ColumnInfo(name = "count") val count: Int?
)