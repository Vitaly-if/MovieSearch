package com.example.main_movies.presentation.adapters.genres

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main_movies.databinding.ItemGenreBinding
import com.example.main_movies.presentation.Genre
import com.google.android.material.color.MaterialColors

class GenresAdapter(
    private val onEventClick: (Genre) -> Unit,
) : ListAdapter<Genre, GenresAdapter.GenreViewHolder>(GenresDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GenreViewHolder(
        private val binding: ItemGenreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {

            with(binding) {
                genreName.text = capitalizeFirstLetter(genre.name)
                println(genre.name)
                if (genre.selected)
                    binding.root.setBackgroundColor(
                        MaterialColors.getColor(
                            binding.root,
                            com.google.android.material.R.attr.colorPrimaryContainer
                        )
                    )
                else binding.root.setBackgroundColor(Color.TRANSPARENT)
                root.setOnClickListener { onEventClick(genre) }
            }
        }
    }

    private fun capitalizeFirstLetter(text: String): String {
        return if (text.isEmpty()) {
            text
        } else {
            text.substring(0, 1).uppercase() + text.substring(1)
        }
    }
}

