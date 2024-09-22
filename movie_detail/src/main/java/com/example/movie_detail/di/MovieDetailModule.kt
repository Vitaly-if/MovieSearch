package com.example.movie_detail.di

import com.example.movie_detail.data.MovieDetailRepositoryImpl
import com.example.movie_detail.domain.LoadMovieUseCase
import com.example.movie_detail.domain.MovieDetailRepository
import com.example.movie_detail.presentation.MovieDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val MovieDetailModule = module {
    viewModelOf(::MovieDetailViewModel)
    singleOf(::LoadMovieUseCase)
    single<MovieDetailRepository> {
        MovieDetailRepositoryImpl(get())
    }
}