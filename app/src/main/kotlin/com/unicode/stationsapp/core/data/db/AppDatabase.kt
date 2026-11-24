package com.unicode.stationsapp.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unicode.stationsapp.stations.data.dao.DeviceDao
import com.unicode.stationsapp.stations.data.dao.StationDao
import com.unicode.stationsapp.stations.data.entity.DeviceEntity
import com.unicode.stationsapp.stations.data.entity.StationEntity

@Database(
    entities = [StationEntity::class, DeviceEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
    abstract fun deviceDao(): DeviceDao
}