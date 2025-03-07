package com.banquemisr.homecomponent.domain.usecase

import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.homecomponent.domain.repository.MovieTypesRepository
import com.banquemisr.shared.BMResult
import javax.inject.Inject

internal class FetchMoviesByTypeUseCase @Inject constructor(
    private val movieTypesRepository: MovieTypesRepository
) : FetchMoviesByType {
    override suspend fun invoke(type: String): BMResult<MoviesType, Unit> {
        return movieTypesRepository.fetchMoviesByType(type = type)
    }
}
