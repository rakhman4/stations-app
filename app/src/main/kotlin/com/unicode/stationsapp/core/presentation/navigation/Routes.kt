package com.unicode.stationsapp.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object Stations : Routes

    @Serializable
    data class StationDetail(val id: String) : Routes
}