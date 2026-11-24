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
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer
import com.unicode.stationsapp.stations.presentation.component.BackToStationsButton
import com.unicode.stationsapp.stations.presentation.component.DeviceItem
import com.unicode.stationsapp.stations.presentation.component.StationSummary
import com.unicode.stationsapp.stations.presentation.preview.mockStationDetail
import com.unicode.stationsapp.stations.presentation.viewmodel.StationDetailUiState
import com.unicode.stationsapp.stations.presentation.viewmodel.StationDetailViewModel
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsLoadStatus
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StationDetailScreen(
    screenPadding: PaddingValues,
    stationId: String,
    navController: NavHostController
) {
    val viewModel = koinViewModel<StationDetailViewModel>(parameters = { parametersOf(stationId) })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StationDetailScreen(
        screenPadding = screenPadding,
        uiState = uiState,
        onRetry = viewModel::refresh,
        onNavigateBack = { navController.navigateUp() }
    )
}

@Composable
private fun StationDetailScreen(
    screenPadding: PaddingValues = PaddingValues(),
    uiState: StationDetailUiState,
    onRetry: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val detail = uiState.detail
    val devices = detail?.devices.orEmpty()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            BackToStationsButton(onNavigateBack = onNavigateBack)
        }

        item {
            if (detail == null) {
                Text(stringResource(R.string.station_detail_title), style = MaterialTheme.typography.headlineMedium)
            } else {
                StationSummary(detail.station)
            }
        }

        if (detail != null) item {
            Text(
                text = stringResource(R.string.station_devices_title),
                style = MaterialTheme.typography.titleLarge
            )
        }

        when (uiState.status) {
            StationsLoadStatus.Loading -> item { LoadingIndicator() }
            StationsLoadStatus.Failed -> item {
                val message = when {
                    detail == null -> R.string.station_detail_load_failed
                    devices.isEmpty() -> R.string.station_devices_load_failed
                    else -> R.string.station_devices_refresh_failed
                }
                RetryMessage(message = stringResource(message), onRetry = onRetry)
            }
            StationsLoadStatus.Loaded -> if (devices.isEmpty()) {
                item { Text(stringResource(R.string.station_devices_empty)) }
            }
        }

        items(devices, key = { it.id }) { device -> DeviceItem(device) }
    }
}

@Preview
@Composable
private fun StationDetailScreenPreview() {
    PreviewContainer {
        StationDetailScreen(
            uiState = StationDetailUiState(detail = mockStationDetail, status = StationsLoadStatus.Failed),
            onRetry = {},
            onNavigateBack = {}
        )
    }
}