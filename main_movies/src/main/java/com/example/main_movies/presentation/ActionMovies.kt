package com.example.main_movies.presentation

import com.example.main_movies.domain.entity.FilmEntity

interface ActionMovies {
    fun clickOnGenre(genreSelected: Genre)
    fun clickOnFilm(filmEntity: FilmEntity)
}
