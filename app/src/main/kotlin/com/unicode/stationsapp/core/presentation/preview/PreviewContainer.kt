package com.unicode.stationsapp.core.presentation.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.unicode.stationsapp.ui.theme.StationsAppTheme

@Composable
internal fun PreviewContainer(content: @Composable () -> Unit) {
    StationsAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(contentAlignment = Alignment.Center) {
                content()
            }
        }
    }
}