package com.unicode.stationsapp.stations.di

import com.unicode.stationsapp.core.data.db.AppDatabase
import com.unicode.stationsapp.stations.data.repository.StationRepositoryImpl
import com.unicode.stationsapp.stations.data.source.StationRemoteDataSource
import com.unicode.stationsapp.stations.domain.repository.StationRepository
import com.unicode.stationsapp.stations.domain.usecase.ObserveStationsUseCase
import com.unicode.stationsapp.stations.domain.usecase.RefreshStationsUseCase
import com.unicode.stationsapp.stations.presentation.viewmodel.StationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stationModule = module {
    single { get<AppDatabase>().stationDao() }
    single { StationRemoteDataSource(client = get()) }

    single<StationRepository> {
        StationRepositoryImpl(remoteDataSource = get(), stationDao = get())
    }

    single { ObserveStationsUseCase(repository = get()) }
    single { RefreshStationsUseCase(repository = get()) }

    viewModel {
        StationsViewModel(observeStations = get(), refreshStations = get())
    }
}