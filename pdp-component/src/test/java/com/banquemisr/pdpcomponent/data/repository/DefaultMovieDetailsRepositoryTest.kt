package com.banquemisr.pdpcomponent.data.repository

import com.banquemisr.pdpcomponent.data.api.MovieDetailsApiService
import com.banquemisr.pdpcomponent.data.mapper.MovieDetailsMapper
import com.banquemisr.pdpcomponent.data.model.*
import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.shared.BMResult
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.mockito.kotlin.*

class DefaultMovieDetailsRepositoryTest {
    private val moviesByTypeApiService: MovieDetailsApiService = mock()
    private val mapper: MovieDetailsMapper = mock()
    private val sut = DefaultMovieDetailsRepository(moviesByTypeApiService, mapper)

    @Test
    fun `EXPECT success result WHEN api returns 200`() = runTest {
        whenever(moviesByTypeApiService.fetchMovieDetails(id = MOVIE_ID)).thenReturn(EMPTY_MOVIES_DETAILS_DTO)
        whenever(mapper.transform(EMPTY_MOVIES_DETAILS_DTO)).thenReturn(EMPTY_MOVIES_DETAILS)

        val actual = sut.fetchMovieDetails(MOVIE_ID).asSuccess().data

        Assert.assertEquals(EMPTY_MOVIES_DETAILS, actual)
    }

    @Test
    fun `EXPECT error result WHEN api returns error`() = runTest {
        whenever(moviesByTypeApiService.fetchMovieDetails(id = MOVIE_ID)).thenAnswer { Throwable() }

        val actual = sut.fetchMovieDetails(MOVIE_ID).asError()

        Assert.assertEquals(BMResult.Error(Unit), actual)
    }

    private companion object {
        const val MOVIE_ID = "12312"
        val EMPTY_MOVIES_DETAILS_DTO = MovieDetailsDto(
            id = 12,
            title = "title",
            posterPath = "poster",
            overview = "overview",
            releaseDate = "1-1-2020",
            voteAverage = 1.5,
            status = "status",
            genres = listOf(MovieGenre(1, "genre"))
        )
        val EMPTY_MOVIES_DETAILS = MovieDetails(
            id = 12,
            title = "title",
            posterPath = "poster",
            overview = "overview",
            releaseDate = "1-1-2020",
            voteAverage = 1.5,
            status = "status",
            genres = listOf("genre")
        )
    }
}
