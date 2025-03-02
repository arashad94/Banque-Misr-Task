package com.banquemisr.homecomponent.data.api

import com.banquemisr.homecomponent.data.model.MoviesTypeDto
import retrofit2.http.*

// Those belongs to user-component library but for simplicity they will reside here
internal const val AUTHORIZATION_HEADER = "Authorization"
internal const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MzlhYTRkNDkxNzNlZTE0YzNmYTAzOTExMmNhNjFlNSIsIm5iZiI6MTc0MDg3OTE3OC40ODgsInN1YiI6IjY3YzNiNTRhODlhZmZmOWUzMjRlMGFjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xA-hlcxzhrmjDH-Tm195gutA0-n3IE6dmxgA3aB5s9I"

internal interface MoviesByTypeApiService {
    @GET("movie/{movie_type}")
    suspend fun getMovieByType(
        @Header(AUTHORIZATION_HEADER) authHeader: String = TOKEN,
        @Path(value = "movie_type") type: String
    ): MoviesTypeDto
}
