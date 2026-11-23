package com.unicode.stationsapp.stations.presentation.preview

import com.unicode.stationsapp.stations.domain.model.Station

internal val mockStation = Station(
    id = "preview-brno",
    name = "Brno - Kralovo Pole",
    address = "Palackeho trida 1946/112, 612 00 Brno",
    country = "CZ",
    deviceCount = 2
)

internal val mockStations = listOf(
    mockStation,
    Station(
        id = "preview-prague",
        name = "Prague - Chodov",
        address = "Roztylska 2321/19, 148 00 Prague",
        country = "CZ",
        deviceCount = 0
    )
)