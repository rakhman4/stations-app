package com.unicode.stationsapp.stations.data.source

import com.unicode.stationsapp.stations.data.dto.DeviceDto
import com.unicode.stationsapp.stations.data.dto.StationDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodeURLPathPart

private const val STATIONS_URL = "https://test-ucs-mock-api.vercel.app/api/v1/stations"

class StationRemoteDataSource(private val client: HttpClient) {
    suspend fun fetchStations(): List<StationDto> = client.get(STATIONS_URL).body()

    suspend fun fetchDevices(stationId: String): List<DeviceDto> =
        client.get("$STATIONS_URL/${stationId.encodeURLPathPart()}/devices").body()
}