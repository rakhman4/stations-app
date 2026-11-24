package com.unicode.stationsapp.stations.data.mapper

import com.unicode.stationsapp.stations.data.dto.DeviceDto
import com.unicode.stationsapp.stations.data.entity.DeviceEntity
import com.unicode.stationsapp.stations.domain.model.Device

fun DeviceDto.toEntity(stationId: String) = DeviceEntity(
    stationId = stationId,
    id = id,
    name = name,
    type = type,
    status = status,
    lastSeen = lastSeen
)

fun DeviceEntity.toDomain() = Device(
    id = id,
    name = name,
    type = type,
    status = status,
    lastSeen = lastSeen
)