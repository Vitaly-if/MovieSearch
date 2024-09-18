package com.example.main_movies.data

import api.ApiService
import com.example.main_movies.domain.MoviesRepository
import com.example.main_movies.domain.entity.FilmEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MoviesRepositoryImpl(private val apiService: ApiService) : MoviesRepository {
    private var defaultFilmEntity = MutableStateFlow<Result<List<FilmEntity>>?>(null)
    override fun observeMovies(): Flow<Result<List<FilmEntity>>> =
        defaultFilmEntity.asStateFlow().filterNotNull()

    override suspend fun loadMovies() {
        val movie = apiService.loadMovies()
        defaultFilmEntity.value = Result.success(movie.films.map { it.toDomainMapped() })
    }
}