package dto

import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("films" ) var films : ArrayList<Film> = arrayListOf()
)