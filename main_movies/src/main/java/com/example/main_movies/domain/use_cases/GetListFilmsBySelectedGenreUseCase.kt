package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.entity.FilmEntity
import com.example.main_movies.presentation.Genre

class GetListFilmsBySelectedGenreUseCase {
    fun invoke(selectedGenre: Genre, listFilmDefault: List<FilmEntity>): List<FilmEntity> =
        if (!selectedGenre.selected)
            listFilmDefault.filter { it.genres.contains(selectedGenre.name) } else listFilmDefault
}
