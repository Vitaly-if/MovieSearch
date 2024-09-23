package com.example.main_movies.domain.use_cases

import com.example.main_movies.domain.entity.FilmEntity
import com.example.main_movies.presentation.Genre

class MergedGenreFromAllFilmsUseCase {
    fun invoke(allFilms: List<FilmEntity>) = allFilms.getAllGenres()

    private fun List<FilmEntity>.getAllGenres(): List<Genre> {
        val mergedList = mutableListOf<String>()
        this.forEach { mergedList.addAll(it.genres) }
        return mergedList.toSet().sorted().map { Genre(name = it) }.toList()
    }
}