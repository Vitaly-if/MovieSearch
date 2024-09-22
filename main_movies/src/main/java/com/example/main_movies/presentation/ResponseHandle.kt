package com.example.main_movies.presentation

import com.example.main_movies.domain.entity.FilmEntity

interface ResponseHandle {

    fun <T> showError(result: Result<T>)

    fun showSuccess(result: Result<List<FilmEntity>>)
}