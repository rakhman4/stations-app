package com.unicode.stationsapp.stations.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.navigation.Routes
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsLoadStatus
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsUiState
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StationsScreen(
    screenPadding: PaddingValues,
    navController: NavHostController
) {
    val viewModel = koinViewModel<StationsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StationsScreen(
        screenPadding = screenPadding,
        uiState = uiState,
        onRetry = viewModel::refresh,
        onStationClick = { stationId ->
            navController.navigate(Routes.StationDetail(id = stationId)) {
                launchSingleTop = true
            }
        }
    )
}

@Composable
private fun StationsScreen(
    screenPadding: PaddingValues,
    uiState: StationsUiState,
    onRetry: () -> Unit,
    onStationClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.stations_title),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        when (uiState.status) {
            StationsLoadStatus.Loading -> item {
                CircularProgressIndicator()
            }

            StationsLoadStatus.Failed -> item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        stringResource(
                            if (uiState.stations.isEmpty()) R.string.stations_load_failed
                            else R.string.stations_refresh_failed
                        )
                    )
                    Button(onClick = onRetry) {
                        Text(stringResource(R.string.stations_retry))
                    }
                }
            }

            StationsLoadStatus.Loaded -> if (uiState.stations.isEmpty()) {
                item { Text(stringResource(R.string.stations_empty)) }
            }
        }

        items(uiState.stations, key = { it.id }) { station ->
            StationItem(station = station, onClick = { onStationClick(station.id) })
        }
    }
}

@Composable
private fun StationItem(station: Station, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(station.name, style = MaterialTheme.typography.titleMedium)
            Text(station.address, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = pluralStringResource(
                    R.plurals.station_device_count,
                    station.deviceCount,
                    station.deviceCount
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}