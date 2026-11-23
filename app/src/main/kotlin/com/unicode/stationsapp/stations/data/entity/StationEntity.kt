package com.unicode.stationsapp.stations.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StationEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val country: String,
    val deviceCount: Int
)