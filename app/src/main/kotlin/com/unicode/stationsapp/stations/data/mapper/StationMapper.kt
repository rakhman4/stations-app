package com.unicode.stationsapp.stations.data.mapper

import com.unicode.stationsapp.stations.data.dto.StationDto
import com.unicode.stationsapp.stations.data.entity.StationEntity
import com.unicode.stationsapp.stations.domain.model.Station

fun StationDto.toEntity() = StationEntity(
    id = id,
    name = name,
    address = address,
    country = country,
    deviceCount = deviceCount
)

fun StationEntity.toDomain() = Station(
    id = id,
    name = name,
    address = address,
    country = country,
    deviceCount = deviceCount
)