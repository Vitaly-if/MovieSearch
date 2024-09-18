package com.example.main_movies.presentation.adapters.movies

import androidx.recyclerview.widget.DiffUtil
import com.example.main_movies.domain.entity.FilmEntity

class MoviesDiffCallback : DiffUtil.ItemCallback<FilmEntity>() {
    override fun areItemsTheSame(
        oldItem: FilmEntity,
        newItem: FilmEntity
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: FilmEntity,
        newItem: FilmEntity
    ): Boolean =
        oldItem == newItem
}