package com.example.main_movies.domain

import com.example.main_movies.domain.entity.FilmEntity

interface MoviesRepository {
    suspend fun loadMovies(): Result<List<FilmEntity>>

    suspend fun saveMovie(filmEntity: FilmEntity): Result<Unit>

}