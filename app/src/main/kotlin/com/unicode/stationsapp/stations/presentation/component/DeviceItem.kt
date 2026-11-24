package com.unicode.stationsapp.stations.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer
import com.unicode.stationsapp.stations.domain.model.Device
import com.unicode.stationsapp.stations.presentation.preview.mockDevice

@Composable
internal fun DeviceItem(device: Device, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(device.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = stringResource(R.string.station_device_type_and_status, device.type, device.status),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun DeviceItemPreview() {
    PreviewContainer {
        DeviceItem(device = mockDevice, modifier = Modifier.padding(16.dp))
    }
}