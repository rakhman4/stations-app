package com.unicode.stationsapp.stations.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StationDto(
    val id: String,
    val name: String,
    val address: String,
    val country: String,
    val deviceCount: Int
)