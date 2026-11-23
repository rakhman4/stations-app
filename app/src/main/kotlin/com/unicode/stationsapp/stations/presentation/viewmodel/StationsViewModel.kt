package com.unicode.stationsapp.stations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.domain.usecase.ObserveStationsUseCase
import com.unicode.stationsapp.stations.domain.usecase.RefreshStationsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StationsViewModel(
    private val observeStations: ObserveStationsUseCase,
    private val refreshStations: RefreshStationsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(StationsUiState())
    val uiState = _uiState.asStateFlow()

    private var refreshJob: Job? = null

    init {
        refresh()
    }

    fun refresh() {
        if (refreshJob?.isActive == true) return

        refreshJob = viewModelScope.launch {
            val cachedStations = readSavedStations() ?: _uiState.value.stations
            _uiState.value = StationsUiState(cachedStations, StationsLoadStatus.Loading)

            val refreshedStations = if (refreshStations().isSuccess) readSavedStations() else null
            _uiState.value = if (refreshedStations == null) {
                StationsUiState(cachedStations, StationsLoadStatus.Failed)
            } else {
                StationsUiState(refreshedStations, StationsLoadStatus.Loaded)
            }
        }
    }

    private suspend fun readSavedStations(): List<Station>? = try {
        observeStations().first()
    } catch (exception: CancellationException) {
        throw exception
    } catch (_: Exception) {
        null
    }
}