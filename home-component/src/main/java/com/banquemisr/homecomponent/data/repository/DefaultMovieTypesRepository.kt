package com.banquemisr.homecomponent.data.repository

import com.banquemisr.bmcache.CacheManager
import com.banquemisr.homecomponent.data.api.MoviesByTypeApiService
import com.banquemisr.homecomponent.data.mapper.MoviesByTypeMapper
import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.homecomponent.domain.repository.MovieTypesRepository
import com.banquemisr.shared.BMResult
import javax.inject.Inject

internal class DefaultMovieTypesRepository @Inject constructor(
    private val moviesByTypeApiService: MoviesByTypeApiService,
    private val moviesByTypeMapper: MoviesByTypeMapper,
    private val cacheManager: CacheManager<MoviesType>
) : MovieTypesRepository {
    override suspend fun fetchMoviesByType(type: String): BMResult<MoviesType, Unit> {
        val cachedData = cacheManager.get(type, TTL)
        if (cachedData != null) {
            return BMResult.Success(cachedData) // Return cached data
        }

        return try {
            val movieTypesDto = moviesByTypeApiService.getMovieByType(type = type)
            val moviesType = moviesByTypeMapper.transform(movieTypesDto)
            cacheManager.put(type, moviesType)
            BMResult.Success(moviesType)
        } catch (e: Exception) {
            BMResult.Error(Unit)
        }
    }

    companion object {
        private const val TTL = 5 * 60 * 1000L // 5 minutes
    }
}
