package com.example.main_movies.domain.entity

import com.example.main_movies.presentation.Genre

data class FilmEntity(
    val id: Int,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double,
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)