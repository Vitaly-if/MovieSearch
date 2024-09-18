package com.example.main_movies.presentation.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main_movies.databinding.ItemGanreBinding
import com.example.main_movies.domain.entity.FilmEntity


class MoviesAdapter (
    private val onFilmClick: (FilmEntity) -> Unit,
) :  ListAdapter<FilmEntity, MoviesAdapter.FilmViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding =
            ItemGanreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FilmViewHolder(
        private val binding: ItemGanreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(FilmEntity: FilmEntity) {

            with(binding) {
//                val picture = String.format("%s%s", BASE_IMAGE_URL, event.eventPhoto.firstOrNull())
//                if (event.eventPhoto.isNotEmpty()) {
//                    Glide.with(context)
//                        .load(picture).into(eventIcon)
//                } else {
//                    eventIcon.setImageResource(placeHolder(event.eventTypeName, context))
//                }
//                eventTitle.text = event.eventName
//                eventDate.text = date
//                eventStartTime.text = time
//                eventLocation.text = event.eventAddress?.city
//                isFinishedState(event, binding)
//                eventDeleted.isVisible = event.isDeleted
//                alcoholIcon.isVisible = event.isPresenceOfAlcohol
//                deleteIcon.setOnClickListener { eventDelete(event) }
                root.setOnClickListener { onFilmClick(FilmEntity) }
            }
        }
    }

//    private fun isFinishedState(event: EventResponseItem, binding: ItemFavoritesBinding) {
//        if (event.isFinished) {
//            binding.eventIcon.alpha = TRANSPARENCY
//            binding.containerText.alpha = TRANSPARENCY
//        } else {
//            binding.eventIcon.alpha = NO_TRANSPARENCY
//            binding.containerText.alpha = NO_TRANSPARENCY
//        }
}