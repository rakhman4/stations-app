package com.unicode.stationsapp.stations.domain.usecase

import com.unicode.stationsapp.stations.domain.repository.StationRepository

class ObserveStationsUseCase(private val repository: StationRepository) {
    operator fun invoke() = repository.observeStations()
}