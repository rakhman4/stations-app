package com.unicode.stationsapp.stations.domain.usecase

import com.unicode.stationsapp.stations.domain.repository.StationRepository

class RefreshStationDevicesUseCase(private val repository: StationRepository) {
    suspend operator fun invoke(stationId: String) = repository.refreshDevices(stationId)
}