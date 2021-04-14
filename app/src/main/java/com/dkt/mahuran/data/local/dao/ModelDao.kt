package com.dkt.mahuran.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dkt.mahuran.data.local.entity.ModelEntity

@Dao
interface ModelDao {
    @Query("SELECT mde.id FROM modelentity as mde WHERE mde.model_code = :modelCode")
    fun getModelCode(modelCode: String): String

    @Insert
    fun insertModel(vararg modelEntity: ModelEntity)
}