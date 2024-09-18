package com.example.main_movies.data

import com.example.main_movies.domain.entity.FilmEntity
import dto.Film

fun Film.toDomainMapped(): FilmEntity {
    return FilmEntity(
        id = id ?: 1,
        localizedName = localizedName?: "",
        name = name?: "",
        year = year?: 0,
        rating = rating?: 0.0,
        imageUrl = imageUrl?: "",
        description = description?: "",
        genres = genres
    )
}