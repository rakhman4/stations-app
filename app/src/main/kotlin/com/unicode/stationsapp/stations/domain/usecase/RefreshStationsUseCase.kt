package com.unicode.stationsapp.stations.domain.usecase

import com.unicode.stationsapp.stations.domain.repository.StationRepository

class RefreshStationsUseCase(private val repository: StationRepository) {
    suspend operator fun invoke() = repository.refreshStations()
}