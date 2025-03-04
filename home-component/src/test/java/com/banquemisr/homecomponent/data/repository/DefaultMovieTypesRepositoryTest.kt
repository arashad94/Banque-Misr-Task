package com.banquemisr.homecomponent.data.repository

import com.banquemisr.homecomponent.data.api.MoviesByTypeApiService
import com.banquemisr.homecomponent.data.mapper.MoviesByTypeMapper
import com.banquemisr.homecomponent.data.model.MoviesTypeDto
import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.shared.BMResult
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.mockito.kotlin.*

class DefaultMovieTypesRepositoryTest {
    private val moviesByTypeApiService: MoviesByTypeApiService = mock()
    private val mapper: MoviesByTypeMapper = mock()
    private val sut = DefaultMovieTypesRepository(moviesByTypeApiService, mapper)

    @Test
    fun `EXPECT success result WHEN api returns 200`() = runTest {
        whenever(moviesByTypeApiService.getMovieByType(type = MOVIE_TYPE)).thenReturn(EMPTY_MOVIES_TYPE_DTO)
        whenever(mapper.transform(EMPTY_MOVIES_TYPE_DTO)).thenReturn(EMPTY_MOVIES_TYPE)

        val actual = sut.fetchMoviesByType(MOVIE_TYPE).asSuccess().data

        Assert.assertEquals(EMPTY_MOVIES_TYPE, actual)
    }

    @Test
    fun `EXPECT error result WHEN api returns error`() = runTest {
        whenever(moviesByTypeApiService.getMovieByType(type = MOVIE_TYPE)).thenAnswer { Throwable() }

        val actual = sut.fetchMoviesByType(MOVIE_TYPE).asError()

        Assert.assertEquals(BMResult.Error(Unit), actual)
    }

    private companion object {
        const val MOVIE_TYPE = "type"
        val EMPTY_MOVIES_TYPE_DTO = MoviesTypeDto(emptyList())
        val EMPTY_MOVIES_TYPE = MoviesType(emptyList())
    }
}
