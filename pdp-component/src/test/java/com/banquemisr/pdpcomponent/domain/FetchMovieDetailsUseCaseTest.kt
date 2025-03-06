package com.banquemisr.pdpcomponent.domain

import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.pdpcomponent.domain.repository.MovieDetailsRepository
import com.banquemisr.pdpcomponent.domain.usecase.FetchMovieDetailsUseCase
import com.banquemisr.shared.*
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.mockito.kotlin.*

class FetchMovieDetailsUseCaseTest {
    private val repository: MovieDetailsRepository = mock()
    private val sut = FetchMovieDetailsUseCase(repository)

    @Test
    fun `EXPECT valid object WHEN repo returns valid response`() = runTest {
        whenever(repository.fetchMovieDetails(ID)).thenReturn(BMResult.Success(MOVIE_DETAILS))

        val actual = sut.invoke(ID).asSuccess().data

        Assert.assertEquals(MOVIE_DETAILS, actual)
    }

    @Test
    fun `EXPECT error returned WHEN repo returns error`() = runTest {
        val error = BMResult.Error(Unit)
        whenever(repository.fetchMovieDetails(ID)).thenReturn(error)

        val actual = sut.invoke(ID).asError()

        Assert.assertEquals(error, actual)
    }

    private companion object {
        const val ID = "12312"
        val MOVIE_DETAILS = fixtureOf<MovieDetails>()
    }
}
