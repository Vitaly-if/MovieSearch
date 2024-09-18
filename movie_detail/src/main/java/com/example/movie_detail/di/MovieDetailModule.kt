package com.example.movie_detail.di

import com.example.movie_detail.presentation.MovieDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val MovieDetailModule = module {
    viewModelOf(::MovieDetailViewModel)
}