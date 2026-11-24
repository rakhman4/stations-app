package com.unicode.stationsapp.stations.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.unicode.stationsapp.stations.data.entity.DeviceEntity

@Dao
abstract class DeviceDao {
    @Query("DELETE FROM devices WHERE stationId = :stationId")
    protected abstract suspend fun deleteDevices(stationId: String)

    @Insert
    protected abstract suspend fun insertDevices(devices: List<DeviceEntity>)

    @Transaction
    open suspend fun replaceDevices(stationId: String, devices: List<DeviceEntity>) {
        deleteDevices(stationId)
        insertDevices(devices)
    }
}