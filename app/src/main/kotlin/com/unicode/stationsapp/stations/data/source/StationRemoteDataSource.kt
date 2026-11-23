package com.unicode.stationsapp.stations.data.source

import com.unicode.stationsapp.stations.data.dto.StationDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val STATIONS_URL = "https://test-ucs-mock-api.vercel.app/api/v1/stations"

class StationRemoteDataSource(private val client: HttpClient) {
    suspend fun fetchStations(): List<StationDto> = client.get(STATIONS_URL).body()
}