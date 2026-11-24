package com.unicode.stationsapp.stations.presentation.viewmodel

import com.unicode.stationsapp.stations.domain.model.StationDetail

data class StationDetailUiState(
    val detail: StationDetail? = null,
    val status: StationsLoadStatus = StationsLoadStatus.Loading
)