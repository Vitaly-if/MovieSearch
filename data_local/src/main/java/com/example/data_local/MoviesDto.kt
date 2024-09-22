package com.example.data_local

import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val id: Int,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double,
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)