package com.example.main_movies.presentation

import com.example.main_movies.R
import com.example.main_movies.domain.ServerResponseError
import com.example.main_movies.domain.entity.FilmEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException

class MoviesStateDelegate(
) :
    HandleSateUi,
    ActionMovies,
    ResponseHandle,
    StateProvider<MoviesUiState> {

    private val _state = MutableStateFlow(MoviesUiState())
    override val state: StateFlow<MoviesUiState>
        get() = _state

    override fun clickOnGenre(genreSelected: Genre) {
        val listFilms = _state.value.copy()
        val filterFilms = if (!genreSelected.selected)
            listFilms.filmEntitiesDefault.filter { it.genres.contains(genreSelected.name) } else listFilms.filmEntitiesDefault

        _state.update {
            it.copy(
                filmEntities = filterFilms.sortedBy { film -> film.localizedName },
                genres = it.genres.map { genre ->
                    if (genreSelected == genre)
                        genre.copy(selected = !genre.selected)
                    else genre.copy(selected = false)
                }
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
            val films = filmEntities.sortedBy { film -> film.localizedName }
            _state.update {
                it.copy(
                    loadingState = LoadingState.Success,
                    filmEntities = films,
                    filmEntitiesDefault = films,
                    genres = filmEntities.getAllGenres()
                )
            }
        }
    }

    fun setLoadingState(state: LoadingState) {
        _state.update { it.copy(loadingState = state) }
    }


    private fun List<FilmEntity>.getAllGenres(): List<Genre> {
        val mergedList = mutableListOf<String>()
        this.forEach { mergedList.addAll(it.genres) }
        return mergedList.toSet().sorted().map { Genre(name = it) }.toList()
    }
}