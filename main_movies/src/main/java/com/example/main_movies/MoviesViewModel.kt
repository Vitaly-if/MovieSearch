package com.example.main_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main_movies.presentation.ActionMovies
import com.example.main_movies.presentation.HandleSateUi
import com.example.main_movies.presentation.MoviesStateDelegate
import com.example.main_movies.presentation.MoviesUiState
import com.example.main_movies.presentation.StateProvider
import com.example.main_movies.domain.use_cases.LoadMoviesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MoviesViewModel(

    private val moviesStateDelegate: MoviesStateDelegate,
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel(), KoinComponent, HandleSateUi by moviesStateDelegate,
    ActionMovies by moviesStateDelegate,
    StateProvider<MoviesUiState> {

    override val state: StateFlow<MoviesUiState>
        get() = moviesStateDelegate.state

    fun loadMovies() {
        viewModelScope.launch {
            loadMoviesUseCase.invoke()

        }
    }
}