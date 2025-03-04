package com.banquemisr.pdpcomponent.data.repository

import com.banquemisr.pdpcomponent.data.api.MovieDetailsApiService
import com.banquemisr.pdpcomponent.data.mapper.MovieDetailsMapper
import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.pdpcomponent.domain.repository.MovieDetailsRepository
import com.banquemisr.shared.BMResult
import javax.inject.Inject

internal class DefaultMovieDetailsRepository @Inject constructor(
    private val apiService: MovieDetailsApiService,
    private val mapper: MovieDetailsMapper
) : MovieDetailsRepository {
    override suspend fun fetchMovieDetails(id: String): BMResult<MovieDetails, Unit> {
        return try {
            val response = apiService.fetchMovieDetails(id = id)
            BMResult.Success(mapper.transform(response))
        } catch (e: Exception) {
            BMResult.Error(Unit)
        }
    }
}
