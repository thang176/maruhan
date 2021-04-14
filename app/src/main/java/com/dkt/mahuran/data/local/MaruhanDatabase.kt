package com.dkt.mahuran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dkt.mahuran.data.local.dao.HallDao
import com.dkt.mahuran.data.local.dao.MachineDao
import com.dkt.mahuran.data.local.dao.ModelDao
import com.dkt.mahuran.data.local.entity.HallEntity
import com.dkt.mahuran.data.local.entity.MachineEntity
import com.dkt.mahuran.data.local.entity.ModelEntity

@Database(entities = arrayOf(HallEntity::class, ModelEntity::class, MachineEntity::class), version = 1)
abstract class MaruhanDatabase : RoomDatabase() {
    abstract fun hallDao(): HallDao
    abstract fun modelDao(): ModelDao
    abstract fun machineDao(): MachineDao
}