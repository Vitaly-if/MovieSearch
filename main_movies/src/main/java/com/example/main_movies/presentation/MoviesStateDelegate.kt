package com.example.main_movies.presentation

import com.example.main_movies.domain.entity.FilmEntity
import com.example.main_movies.domain.use_cases.ObserveMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MoviesStateDelegate(
observeMovies: ObserveMovies
) :
    HandleSateUi,
    ActionMovies,
    StateProvider<MoviesUiState> {

    private val _state = MutableStateFlow(MoviesUiState())
    override val state: StateFlow<MoviesUiState>
        get() = _state
    val moviesEntity: Flow<Result<List<FilmEntity>>> = observeMovies.flow()
    init {

    }

    fun chooseSocial() {
        _state.update { it.copy(loadingState = LoadingState.Loading) }
    }

    override fun clickOnGenre(genre: Genre) {

    }

    override fun clickOnFilm(filmEntity: FilmEntity) {

    }


}