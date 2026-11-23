package com.unicode.stationsapp.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unicode.stationsapp.R
import com.unicode.stationsapp.core.presentation.preview.PreviewContainer

@Composable
internal fun RetryMessage(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(message)
        Button(onClick = onRetry) {
            Text(stringResource(R.string.stations_retry))
        }
    }
}

@Preview
@Composable
private fun RetryMessagePreview() {
    PreviewContainer {
        RetryMessage(message = stringResource(R.string.stations_load_failed), onRetry = {})
    }
}