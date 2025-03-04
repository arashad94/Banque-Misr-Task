package com.banquemisr.pdpcomponent.domain.usecase

import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.shared.BMResult

fun interface FetchMovieDetails {
    suspend operator fun invoke(id: String): BMResult<MovieDetails, Unit>
}
