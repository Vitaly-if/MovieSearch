package com.example.main_movies.presentation

import kotlinx.coroutines.flow.SharedFlow

interface HandleSateUi {
    val state: SharedFlow<MoviesUiState>
}