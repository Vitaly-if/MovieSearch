package com.example.main_movies.presentation.adapters.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.main_movies.databinding.ItemGanreBinding
import com.example.main_movies.presentation.Genre

class GenresAdapter (
    private val onEventClick: (Genre) -> Unit,
) :  ListAdapter<Genre, GenresAdapter.GenreViewHolder>(GenresDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding =
            ItemGanreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GenreViewHolder(
        private val binding: ItemGanreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {

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
                root.setOnClickListener { onEventClick(genre) }
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

//    companion object {
//        const val BASE_IMAGE_URL = "${BuildConfig.BASE_URL}/api/v1/events/avatars/"
//        private const val TRANSPARENCY = 0.5f
//        private const val NO_TRANSPARENCY = 1f
//    }
