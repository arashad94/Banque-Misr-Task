package com.banquemisr.homecomponent.domain.repository

import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.shared.BMResult

internal interface MovieTypesRepository {
    suspend fun fetchMoviesByType(type: String): BMResult<MoviesType, Unit>
}
