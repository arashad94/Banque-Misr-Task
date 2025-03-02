package com.banquemisr.homecomponent.data.repository

import com.banquemisr.homecomponent.data.api.MoviesByTypeApiService
import com.banquemisr.homecomponent.data.mapper.MoviesByTypeMapper
import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.homecomponent.domain.repository.MovieTypesRepository
import com.banquemisr.shared.BMResult
import javax.inject.Inject

internal class DefaultMovieTypesRepository @Inject constructor(
    private val moviesByTypeApiService: MoviesByTypeApiService,
    private val moviesByTypeMapper: MoviesByTypeMapper
) : MovieTypesRepository {
    override suspend fun fetchMoviesByType(type: String): BMResult<MoviesType, Unit> {
        return try {
            val movieTypesDto = moviesByTypeApiService.getMovieByType(type = type)
            BMResult.Success(moviesByTypeMapper.transform(movieTypesDto))
        } catch (e: Exception) {
            BMResult.Error(Unit)
        }
    }
}
