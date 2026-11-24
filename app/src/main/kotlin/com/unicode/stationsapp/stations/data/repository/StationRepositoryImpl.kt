package com.unicode.stationsapp.stations.data.repository

import com.unicode.stationsapp.stations.data.dao.DeviceDao
import com.unicode.stationsapp.stations.data.dao.StationDao
import com.unicode.stationsapp.stations.data.mapper.toDomain
import com.unicode.stationsapp.stations.data.mapper.toEntity
import com.unicode.stationsapp.stations.data.source.StationRemoteDataSource
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.domain.model.StationDetail
import com.unicode.stationsapp.stations.domain.repository.StationRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StationRepositoryImpl(
    private val remoteDataSource: StationRemoteDataSource,
    private val stationDao: StationDao,
    private val deviceDao: DeviceDao
) : StationRepository {
    override fun observeStations(): Flow<List<Station>> =
        stationDao.observeStations().map { stations -> stations.map { it.toDomain() } }

    override suspend fun refreshStations(): Result<Unit> = try {
        val stations = remoteDataSource.fetchStations().map { it.toEntity() }
        stationDao.replaceStations(stations)
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    override suspend fun getStationDetail(stationId: String): StationDetail? =
        stationDao.getStationWithDevices(stationId)?.let { cached ->
            StationDetail(
                station = cached.station.toDomain(),
                devices = cached.devices.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
                    .map { it.toDomain() }
            )
        }

    override suspend fun refreshDevices(stationId: String): Result<Unit> = try {
        val devices = remoteDataSource.fetchDevices(stationId).map { it.toEntity(stationId) }
        deviceDao.replaceDevices(stationId, devices)
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}