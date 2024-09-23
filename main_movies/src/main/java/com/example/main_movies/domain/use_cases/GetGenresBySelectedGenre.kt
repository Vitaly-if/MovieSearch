package com.example.main_movies.domain.use_cases

import com.example.main_movies.presentation.Genre

class GetGenresBySelectedGenre {
    fun invoke(genres: List<Genre>, selectedGenre: Genre) = genres.map { genre ->
        if (selectedGenre == genre)
            genre.copy(selected = !genre.selected)
        else genre.copy(selected = false)
    }
}