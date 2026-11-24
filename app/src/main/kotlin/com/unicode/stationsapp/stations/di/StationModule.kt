package com.unicode.stationsapp.stations.di

import com.unicode.stationsapp.core.data.db.AppDatabase
import com.unicode.stationsapp.stations.data.repository.StationRepositoryImpl
import com.unicode.stationsapp.stations.data.source.StationRemoteDataSource
import com.unicode.stationsapp.stations.domain.repository.StationRepository
import com.unicode.stationsapp.stations.domain.usecase.GetStationDetailUseCase
import com.unicode.stationsapp.stations.domain.usecase.ObserveStationsUseCase
import com.unicode.stationsapp.stations.domain.usecase.RefreshStationDevicesUseCase
import com.unicode.stationsapp.stations.domain.usecase.RefreshStationsUseCase
import com.unicode.stationsapp.stations.presentation.viewmodel.StationDetailViewModel
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stationModule = module {
    single { get<AppDatabase>().stationDao() }
    single { get<AppDatabase>().deviceDao() }
    single { StationRemoteDataSource(client = get()) }

    single<StationRepository> {
        StationRepositoryImpl(remoteDataSource = get(), stationDao = get(), deviceDao = get())
    }

    single { ObserveStationsUseCase(repository = get()) }
    single { RefreshStationsUseCase(repository = get()) }

    viewModel {
        StationsViewModel(observeStations = get(), refreshStations = get())
    }

    single { GetStationDetailUseCase(repository = get()) }
    single { RefreshStationDevicesUseCase(repository = get()) }
    viewModel { (stationId: String) ->
        StationDetailViewModel(stationId, getStationDetail = get(), refreshDevices = get())
    }
}