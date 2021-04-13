package com.dkt.mahuran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class HallEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "add") val address: String?,
    @ColumnInfo(name = "lat") val lat: Double?,
    @ColumnInfo(name = "long") val lng: Double?,
    @ColumnInfo(name = "machine_invisibled") val machineInvisibled: Int?,
)