package com.unicode.stationsapp.stations.data.repository

import com.unicode.stationsapp.stations.data.dao.StationDao
import com.unicode.stationsapp.stations.data.mapper.toDomain
import com.unicode.stationsapp.stations.data.mapper.toEntity
import com.unicode.stationsapp.stations.data.source.StationRemoteDataSource
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.domain.repository.StationRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StationRepositoryImpl(
    private val remoteDataSource: StationRemoteDataSource,
    private val stationDao: StationDao
) : StationRepository {
    override fun observeStations(): Flow<List<Station>> =
        stationDao.observeStations().map { stations -> stations.map { it.toDomain() } }

    override suspend fun refreshStations(): Result<Unit> = try {
        val stations = remoteDataSource.fetchStations().map { it.toEntity() }
        stationDao.replaceSnapshot(stations)
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}