package com.unicode.stationsapp.stations.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.unicode.stationsapp.stations.data.entity.StationEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StationDao {
    @Query("SELECT * FROM stations ORDER BY name COLLATE NOCASE")
    abstract fun observeStations(): Flow<List<StationEntity>>

    @Query("SELECT id FROM stations")
    protected abstract suspend fun getStationIds(): List<String>

    @Upsert
    protected abstract suspend fun upsertStations(stations: List<StationEntity>)

    @Query("DELETE FROM stations WHERE id IN (:ids)")
    protected abstract suspend fun deleteStations(ids: List<String>)

    @Transaction
    open suspend fun replaceSnapshot(stations: List<StationEntity>) {
        val snapshotIds = stations.mapTo(mutableSetOf()) { it.id }
        val removedIds = getStationIds().filterNot { it in snapshotIds }
        upsertStations(stations)
        if (removedIds.isNotEmpty()) {
            deleteStations(removedIds)
        }
    }
}