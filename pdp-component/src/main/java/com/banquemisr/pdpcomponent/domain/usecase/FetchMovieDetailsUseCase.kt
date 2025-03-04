package com.banquemisr.pdpcomponent.domain.usecase

import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.pdpcomponent.domain.repository.MovieDetailsRepository
import com.banquemisr.shared.BMResult
import javax.inject.Inject

internal class FetchMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : FetchMovieDetails {
    override suspend fun invoke(id: String): BMResult<MovieDetails, Unit> {
        return movieDetailsRepository.fetchMovieDetails(id)
    }
}
