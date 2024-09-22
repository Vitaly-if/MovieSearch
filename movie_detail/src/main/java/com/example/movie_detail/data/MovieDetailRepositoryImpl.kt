package com.example.movie_detail.data

import com.example.data_local.DataStoreManager
import com.example.movie_detail.domain.MovieDetailRepository
import com.example.movie_detail.domain.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class MovieDetailRepositoryImpl(private val dataStoreManager: DataStoreManager) :
    MovieDetailRepository {
    override suspend fun loadMovieFromCache(): Flow<MovieDetailEntity> {
        return dataStoreManager.moviesDtoFlow.mapNotNull { it?.toEntity() }
    }
}