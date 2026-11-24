package com.unicode.stationsapp.stations.presentation.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer

@Composable
internal fun BackToStationsButton(onNavigateBack: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onNavigateBack,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Text(stringResource(R.string.back_to_stations))
    }
}

@Preview
@Composable
private fun BackToStationsButtonPreview() {
    PreviewContainer { BackToStationsButton(onNavigateBack = {}) }
}