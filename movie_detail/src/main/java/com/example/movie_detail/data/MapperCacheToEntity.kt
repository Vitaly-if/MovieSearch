package com.example.movie_detail.data

import com.example.data_local.MoviesDto
import com.example.movie_detail.domain.entity.MovieDetailEntity

fun MoviesDto.toEntity(): MovieDetailEntity {
    return MovieDetailEntity(
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