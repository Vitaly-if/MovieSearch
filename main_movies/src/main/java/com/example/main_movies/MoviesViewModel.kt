package com.example.main_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main_movies.domain.entity.FilmEntity
import com.example.main_movies.presentation.ActionMovies
import com.example.main_movies.presentation.HandleSateUi
import com.example.main_movies.presentation.MoviesStateDelegate
import com.example.main_movies.presentation.MoviesUiState
import com.example.main_movies.presentation.StateProvider
import com.example.main_movies.domain.use_cases.LoadMoviesUseCase
import com.example.main_movies.domain.use_cases.SaveMoviesToCacheUseCase
import com.example.main_movies.presentation.LoadingState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MoviesViewModel(

    private val moviesStateDelegate: MoviesStateDelegate,
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val saveMoviesToCacheUseCase: SaveMoviesToCacheUseCase
) : ViewModel(), KoinComponent, HandleSateUi by moviesStateDelegate,
    ActionMovies by moviesStateDelegate,
    StateProvider<MoviesUiState> {

    override val state: StateFlow<MoviesUiState>
        get() = moviesStateDelegate.state

    fun loadMovies() {
        moviesStateDelegate.setLoadingState(LoadingState.Loading)
        viewModelScope.launch {
            val resultRequest: Result<List<FilmEntity>> = loadMoviesUseCase.invoke()
            if (resultRequest.isFailure) moviesStateDelegate.showError(resultRequest) else
                moviesStateDelegate.showSuccess(resultRequest)
        }
    }

    fun saveMovie(filmEntity: FilmEntity) {
        moviesStateDelegate.setLoadingState(LoadingState.Loading)
        viewModelScope.launch {
            val resultRequest: Result<Unit> = saveMoviesToCacheUseCase.invoke(filmEntity)
            if (resultRequest.isFailure) moviesStateDelegate.showError(resultRequest) else
                moviesStateDelegate.setLoadingState(LoadingState.Navigation)
        }
    }


}