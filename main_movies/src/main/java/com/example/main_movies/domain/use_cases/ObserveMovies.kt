package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.MoviesRepository
import com.example.main_movies.domain.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

class ObserveMovies(private val repository: MoviesRepository) {
    fun flow(): Flow<Result<List<FilmEntity>>> = repository.observeMovies()
}