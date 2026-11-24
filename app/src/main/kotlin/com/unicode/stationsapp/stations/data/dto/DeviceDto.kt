package com.unicode.stationsapp.stations.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeviceDto(
    val id: String,
    val name: String,
    val type: String,
    val status: String,
    val lastSeen: String? = null
)