package com.unicode.stationsapp.stations.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.component.LoadingIndicator
import com.unicode.stationsapp.core.presentation.component.RetryMessage
import com.unicode.stationsapp.core.presentation.navigation.Routes
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer
import com.unicode.stationsapp.stations.presentation.component.StationItem
import com.unicode.stationsapp.stations.presentation.preview.mockStations
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
    screenPadding: PaddingValues = PaddingValues(),
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
                LoadingIndicator()
            }

            StationsLoadStatus.Failed -> item {
                RetryMessage(
                    message = stringResource(
                        if (uiState.stations.isEmpty()) R.string.stations_load_failed
                        else R.string.stations_refresh_failed
                    ),
                    onRetry = onRetry
                )
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

@Preview
@Composable
private fun StationsScreenPreview() {
    PreviewContainer {
        StationsScreen(
            uiState = StationsUiState(stations = mockStations, status = StationsLoadStatus.Failed),
            onRetry = {},
            onStationClick = {}
        )
    }
}