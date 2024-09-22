package com.example.movie_detail.presentation

import com.example.movie_detail.domain.entity.MovieDetailEntity

sealed class DetailMovieUiState() {
    data class Success(val movieDetailEntity: MovieDetailEntity) : DetailMovieUiState()
    data class Error(val error: String) : DetailMovieUiState()
    data object Empty : DetailMovieUiState()
}

