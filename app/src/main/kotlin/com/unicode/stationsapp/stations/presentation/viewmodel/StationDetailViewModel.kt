package com.unicode.stationsapp.stations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unicode.stationsapp.stations.domain.model.StationDetail
import com.unicode.stationsapp.stations.domain.usecase.GetStationDetailUseCase
import com.unicode.stationsapp.stations.domain.usecase.RefreshStationDevicesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StationDetailViewModel(
    private val stationId: String,
    private val getStationDetail: GetStationDetailUseCase,
    private val refreshDevices: RefreshStationDevicesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(StationDetailUiState())
    val uiState = _uiState.asStateFlow()

    private var refreshJob: Job? = null

    init {
        refresh()
    }

    fun refresh() {
        if (refreshJob?.isActive == true) return
        refreshJob = viewModelScope.launch {
            val cachedDetail = readSavedDetail() ?: _uiState.value.detail
            _uiState.value = StationDetailUiState(cachedDetail, StationsLoadStatus.Loading)

            val refreshedDetail = if (cachedDetail != null && refreshDevices(stationId).isSuccess) {
                readSavedDetail()
            } else {
                null
            }
            _uiState.value = if (refreshedDetail == null) {
                StationDetailUiState(cachedDetail, StationsLoadStatus.Failed)
            } else {
                StationDetailUiState(refreshedDetail, StationsLoadStatus.Loaded)
            }
        }
    }

    private suspend fun readSavedDetail(): StationDetail? = try {
        getStationDetail(stationId)
    } catch (exception: CancellationException) {
        throw exception
    } catch (_: Exception) {
        null
    }
}