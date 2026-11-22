package com.unicode.stationsapp.core.presentation

import androidx.compose.runtime.Composable
import com.unicode.stationsapp.core.presentation.navigation.AppRouter
import com.unicode.stationsapp.ui.theme.StationsAppTheme

@Composable
fun App() {
    StationsAppTheme {
        AppRouter()
    }
}