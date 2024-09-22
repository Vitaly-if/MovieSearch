package com.example.movie_detail.domain

class LoadMovieUseCase(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun invoke() = movieDetailRepository.loadMovieFromCache()
}