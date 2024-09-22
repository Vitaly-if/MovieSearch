package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.MoviesRepository
import com.example.main_movies.domain.entity.FilmEntity

class SaveMoviesToCacheUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(filmEntity: FilmEntity) = repository.saveMovie(filmEntity)
}