package com.unicode.stationsapp.stations.domain.repository

import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.domain.model.StationDetail
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    fun observeStations(): Flow<List<Station>>
    suspend fun refreshStations(): Result<Unit>
    suspend fun getStationDetail(stationId: String): StationDetail?
    suspend fun refreshDevices(stationId: String): Result<Unit>
}