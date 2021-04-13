package com.dkt.mahuran.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MachineEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "machine_number") val machineNumber: String?,
    @ColumnInfo(name = "machine_id") val machineId: String?,
    @ColumnInfo(name = "number_of_all_starts") val numberOfAllStarts: Int?,
    @ColumnInfo(name = "available") val available: Boolean?,
    @ColumnInfo(name = "bonus_number") val bonusNumber: Int?,
    @ColumnInfo(name = "toku_rate_h") val tokuRateH: Int?,
    @ColumnInfo(name = "toku_rate_l") val tokuRateL: Int?,
    @ColumnInfo(name = "bonus_prob") val bonusProb: Int?,
)