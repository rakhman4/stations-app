package com.unicode.stationsapp.stations.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.presentation.preview.mockStation

@Composable
internal fun StationSummary(station: Station, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = station.name,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(station.address, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
private fun StationSummaryPreview() {
    PreviewContainer { StationSummary(station = mockStation) }
}