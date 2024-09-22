package com.example.main_movies.data

import com.example.data_local.MoviesDto
import com.example.main_movies.domain.entity.FilmEntity

fun FilmEntity.toCache(): MoviesDto {
    return MoviesDto(
        id = this.id,
        localizedName = this.localizedName,
        name = this.name,
        year = this.year,
        rating = this.rating,
        imageUrl = this.imageUrl,
        description = this.description,
        genres = this.genres
    )
}