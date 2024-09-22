package com.example.movie_detail.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.movie_detail.R

class GenreTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    fun show(genres: List<String>, year: Int) {
        visibility = VISIBLE
        text = context.getString(R.string.genre_year, genres.getFormatGenre(), year.toString())
    }

    private fun List<String>.getFormatGenre(): String {

        if (this.isEmpty()) return ""
        val genreString = this.toString()
        val trimmedString = this.toString().substring(1, genreString.length - 1)

        return trimmedString.replaceFirstChar { it.uppercaseChar() }
    }

}