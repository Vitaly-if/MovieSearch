package com.example.main_movies.presentation

import com.example.main_movies.R
import com.example.main_movies.domain.ServerResponseError
import com.example.main_movies.domain.entity.FilmEntity
import com.example.main_movies.domain.use_cases.GetGenresBySelectedGenre
import com.example.main_movies.domain.use_cases.GetListFilmsBySelectedGenreUseCase
import com.example.main_movies.domain.use_cases.MergedGenreFromAllFilmsUseCase
import com.example.main_movies.domain.use_cases.SortByFilmNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class MoviesStateDelegate(
    private val mergedGenreFromAllFilmsUseCase: MergedGenreFromAllFilmsUseCase,
    private val getListFilmsBySelectedGenreUseCase: GetListFilmsBySelectedGenreUseCase,
    private val sortByFilmNameUseCase: SortByFilmNameUseCase,
    private val getGenresBySelectedGenre: GetGenresBySelectedGenre
) :
    HandleSateUi,
    ActionMovies,
    ResponseHandle,
    StateProvider<MoviesUiState> {

    private val _state = MutableStateFlow(MoviesUiState())
    override val state: StateFlow<MoviesUiState>
        get() = _state

    override fun clickOnGenre(genreSelected: Genre) {
        val filterFilms = getListFilmsBySelectedGenreUseCase.invoke(
            genreSelected,
            _state.value.copy().filmEntitiesDefault
        )
        _state.update {
            it.copy(
                filmEntities = sortByFilmNameUseCase.invoke(filterFilms),
                genres = getGenresBySelectedGenre.invoke(it.genres, genreSelected)
            )
        }
    }

    override fun clickOnFilm(filmEntity: FilmEntity) {

    }

    override fun <T> showError(result: Result<T>) {
        result.onFailure { throwable ->
            val resultError = when (throwable) {
                is IOException -> R.string.io_exception
                is ServerResponseError.ServerException -> R.string.server_exception
                is ServerResponseError.BadRequestException -> R.string.bed_request
                else -> R.string.unknown_exception
            }
            _state.update { it.copy(loadingState = LoadingState.Error(resultError)) }
        }
    }

    override fun showSuccess(result: Result<List<FilmEntity>>) {
        result.getOrNull()?.let { filmEntities ->
            val films = sortByFilmNameUseCase.invoke(filmEntities)
            _state.update {
                it.copy(
                    loadingState = LoadingState.Success,
                    filmEntities = films,
                    filmEntitiesDefault = films,
                    genres = mergedGenreFromAllFilmsUseCase.invoke(filmEntities)
                )
            }
        }
    }

    fun setLoadingState(state: LoadingState) {
        _state.update { it.copy(loadingState = state) }
    }


}