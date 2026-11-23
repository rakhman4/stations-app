package com.unicode.stationsapp.stations.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer
import com.unicode.stationsapp.stations.domain.model.Station
import com.unicode.stationsapp.stations.presentation.preview.mockStation

@Composable
internal fun StationItem(station: Station, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
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

@Preview
@Composable
private fun StationItemPreview() {
    PreviewContainer {
        StationItem(station = mockStation, onClick = {}, modifier = Modifier.padding(16.dp))
    }
}