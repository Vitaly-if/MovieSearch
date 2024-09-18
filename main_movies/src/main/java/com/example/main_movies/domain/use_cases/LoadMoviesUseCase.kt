package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.MoviesRepository

class LoadMoviesUseCase(private val repository: MoviesRepository) {
    suspend fun invoke() = repository.loadMovies()
}