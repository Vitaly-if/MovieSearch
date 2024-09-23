package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.entity.FilmEntity

class SortByFilmNameUseCase {
    fun invoke(films: List<FilmEntity>) = films.sortedBy { film -> film.localizedName }
}