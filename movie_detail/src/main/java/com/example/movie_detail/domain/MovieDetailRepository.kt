package com.example.movie_detail.domain


import com.example.movie_detail.domain.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun loadMovieFromCache(): Flow<MovieDetailEntity>
}