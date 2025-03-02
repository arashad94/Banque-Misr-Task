package com.banquemisr.homecomponent.domain.usecase

import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.shared.BMResult

internal fun interface FetchMoviesByTypeUseCase {
    suspend operator fun invoke(type: String): BMResult<MoviesType, Unit>
}
