package com.unicode.stationsapp.stations.presentation.viewmodel

import com.unicode.stationsapp.stations.domain.model.Station

data class StationsUiState(
    val stations: List<Station> = emptyList(),
    val status: StationsLoadStatus = StationsLoadStatus.Loading
)