package com.example.main_movies.domain

import com.example.main_movies.domain.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun observeMovies(): Flow<Result<List<FilmEntity>>>
    suspend fun loadMovies()
}