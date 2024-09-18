package com.example.main_movies.di

import com.example.main_movies.MoviesViewModel
import com.example.main_movies.data.MoviesRepositoryImpl
import com.example.main_movies.domain.MoviesRepository
import com.example.main_movies.domain.use_cases.LoadMoviesUseCase
import com.example.main_movies.domain.use_cases.ObserveMovies
import com.example.main_movies.presentation.MoviesStateDelegate
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val MoviesMainModule = module {
    viewModelOf(::MoviesViewModel)
    singleOf(::MoviesStateDelegate)
    singleOf(::ObserveMovies)
    singleOf(::LoadMoviesUseCase)
    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }
}