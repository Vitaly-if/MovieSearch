package com.example.main_movies.presentation

import com.example.main_movies.domain.entity.FilmEntity

data class MoviesUiState(
    val genres: List<Genre> = listOf(),
    val filmEntities: List<FilmEntity> = listOf(),
    val filmEntitiesDefault: List<FilmEntity> = listOf(),
    val loadingState: LoadingState = LoadingState.Initial,

    )

sealed interface LoadingState {
    data object Initial : LoadingState
    data object Loading : LoadingState
    data object Success : LoadingState
    data object Navigation : LoadingState
    data class Error(val error: Int) : LoadingState
}

data class Genre(val name: String, val selected: Boolean = false)