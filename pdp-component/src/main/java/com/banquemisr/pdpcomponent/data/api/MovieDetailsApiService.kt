package com.banquemisr.pdpcomponent.data.api

import com.banquemisr.pdpcomponent.data.model.MovieDetailsDto
import retrofit2.http.*

// Those belongs to user-component library but for simplicity they will reside here
internal const val AUTHORIZATION_HEADER = "Authorization"
internal const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MzlhYTRkNDkxNzNlZTE0YzNmYTAzOTExMmNhNjFlNSIsIm5iZiI6MTc0MDg3OTE3OC40ODgsInN1YiI6IjY3YzNiNTRhODlhZmZmOWUzMjRlMGFjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xA-hlcxzhrmjDH-Tm195gutA0-n3IE6dmxgA3aB5s9I"

internal interface MovieDetailsApiService {
    @GET("movie/{id}")
    suspend fun fetchMovieDetails(
        @Header(AUTHORIZATION_HEADER) authHeader: String = TOKEN,
        @Path(value = "id") id: String
    ): MovieDetailsDto
}
