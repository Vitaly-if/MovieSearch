package com.example.main_movies.presentation.adapters.genres

import androidx.recyclerview.widget.DiffUtil
import com.example.main_movies.presentation.Genre

class GenresDiffCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(
        oldItem: Genre,
        newItem: Genre
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Genre,
        newItem: Genre
    ): Boolean =
        oldItem == newItem
}