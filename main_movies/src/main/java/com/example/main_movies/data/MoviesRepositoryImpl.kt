package com.example.main_movies.data

import api.ApiService
import com.example.data_local.DataStoreManager
import com.example.main_movies.domain.MoviesRepository
import com.example.main_movies.domain.ServerResponseError
import com.example.main_movies.domain.entity.FilmEntity


class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager
) : MoviesRepository, ThrowException {

    override suspend fun loadMovies(): Result<List<FilmEntity>> = runCatching {

        val response = apiService.loadMovies()
        if (response.isSuccessful) {
            return@runCatching response.body()?.films?.map { it.toDomainMapped() } ?: listOf()
        } else {
            throw throwException(response.code())
        }
    }

    override suspend fun saveMovie(filmEntity: FilmEntity): Result<Unit> {
        dataStoreManager.saveMyObject(filmEntity.toCache())
        return Result.success(Unit)
    }

    override fun throwException(code: Int): ServerResponseError {
        return when (code) {
            in 500..599 -> ServerResponseError.ServerException
            in 400..499 -> ServerResponseError.BadRequestException
            else -> ServerResponseError.ServerException
        }
    }
}

interface ThrowException {
    fun throwException(code: Int): ServerResponseError
}