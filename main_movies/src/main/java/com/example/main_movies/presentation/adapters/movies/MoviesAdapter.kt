package com.example.main_movies.presentation.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main_movies.databinding.ItemFilmsBinding
import com.example.main_movies.domain.entity.FilmEntity


class MoviesAdapter(
    private val onFilmClick: (FilmEntity) -> Unit,
) : ListAdapter<FilmEntity, MoviesAdapter.FilmViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding =
            ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FilmViewHolder(
        private val binding: ItemFilmsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filmEntity: FilmEntity) {

            with(binding) {
                if (filmEntity.imageUrl.isNotEmpty()) {
                    Glide.with(filmPoster)
                        .load(filmEntity.imageUrl).into(filmPoster)
                }
                movieName.text = filmEntity.localizedName

                root.setOnClickListener { onFilmClick(filmEntity) }
            }
        }
    }
}