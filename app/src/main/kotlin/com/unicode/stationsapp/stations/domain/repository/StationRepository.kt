package com.unicode.stationsapp.stations.domain.repository

import com.unicode.stationsapp.stations.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    fun observeStations(): Flow<List<Station>>
    suspend fun refreshStations(): Result<Unit>
}