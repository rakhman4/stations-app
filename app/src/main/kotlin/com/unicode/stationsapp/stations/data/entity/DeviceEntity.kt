package com.unicode.stationsapp.stations.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "devices",
    primaryKeys = ["stationId", "id"],
    foreignKeys = [ForeignKey(
        entity = StationEntity::class,
        parentColumns = ["id"],
        childColumns = ["stationId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DeviceEntity(
    val stationId: String,
    val id: String,
    val name: String,
    val type: String,
    val status: String,
    val lastSeen: String?
)