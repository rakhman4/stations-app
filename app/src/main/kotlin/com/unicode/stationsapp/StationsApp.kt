package com.unicode.stationsapp

import android.app.Application
import com.unicode.stationsapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class StationsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@StationsApp)
        }
    }
}