package com.banquemisr.pdpcomponent.domain.repository

import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.shared.BMResult

internal interface MovieDetailsRepository {
    suspend fun fetchMovieDetails(id: String): BMResult<MovieDetails, Unit>
}
