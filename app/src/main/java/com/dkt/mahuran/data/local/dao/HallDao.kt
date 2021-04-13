package com.dkt.mahuran.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dkt.mahuran.data.local.entity.HallEntity

@Dao
interface HallDao {
    @Query("SELECT * FROM hallentity")
    fun getAll(): List<HallEntity>

    @Query("SELECT ety.code FROM hallentity as ety WHERE ety.code = :code")
    fun getHallCode(code: String): String

    @Insert
    fun insertAll(vararg hallEntity: HallEntity)
}