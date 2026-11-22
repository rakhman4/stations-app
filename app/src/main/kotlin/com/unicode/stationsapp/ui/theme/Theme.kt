package com.unicode.stationsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val StationsColorScheme = lightColorScheme(
    primary = CoffeeBean,
    onPrimary = AlmondCream,
    primaryContainer = Tan,
    onPrimaryContainer = DarkInk,
    secondary = ToffeeBrown,
    onSecondary = Color.White,
    secondaryContainer = DesertSand,
    onSecondaryContainer = DarkInk,
    background = AlmondCream,
    onBackground = DarkInk,
    surface = AlmondCream,
    onSurface = DarkInk,
    outline = CoffeeBean,
    outlineVariant = FadedCopper
)

@Composable
fun StationsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StationsColorScheme,
        typography = Typography,
        content = content
    )
}