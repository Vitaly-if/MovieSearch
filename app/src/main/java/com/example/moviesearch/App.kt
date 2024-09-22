package com.example.moviesearch

import android.app.Application
import com.example.main_movies.di.MoviesMainModule
import com.example.movie_detail.di.MovieDetailModule
import di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.example.data_local.di.DataStoreModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(MovieDetailModule, MoviesMainModule, NetworkModule, DataStoreModule)
        }
    }
}
