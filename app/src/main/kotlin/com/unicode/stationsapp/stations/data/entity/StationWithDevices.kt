package com.unicode.stationsapp.stations.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class StationWithDevices(
    @Embedded val station: StationEntity,
    @Relation(parentColumn = "id", entityColumn = "stationId")
    val devices: List<DeviceEntity>
)