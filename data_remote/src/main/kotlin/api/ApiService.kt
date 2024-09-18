package api

import dto.Film
import dto.MoviesDto
import retrofit2.http.GET

interface ApiService {

    @GET("films.json")
    suspend fun loadMovies(
    ): MoviesDto

}