package com.unicode.stationsapp.stations.presentation.preview

import com.unicode.stationsapp.stations.domain.model.Device
import com.unicode.stationsapp.stations.domain.model.StationDetail

internal val mockDevice = Device(
    id = "preview-pos",
    name = "Checkout 1",
    type = "POS",
    status = "ONLINE",
    lastSeen = null
)

internal val mockStationDetail = StationDetail(
    station = mockStation,
    devices = listOf(
        mockDevice,
        Device(
            id = "preview-backoffice",
            name = "Backoffice",
            type = "BOS",
            status = "OFFLINE",
            lastSeen = null
        )
    )
)