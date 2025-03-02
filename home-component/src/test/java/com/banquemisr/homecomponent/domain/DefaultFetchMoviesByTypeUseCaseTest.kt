package com.banquemisr.homecomponent.domain

import com.banquemisr.homecomponent.domain.model.MoviesType
import com.banquemisr.homecomponent.domain.repository.MovieTypesRepository
import com.banquemisr.homecomponent.domain.usecase.DefaultFetchMoviesByTypeUseCase
import com.banquemisr.shared.BMResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*

class DefaultFetchMoviesByTypeUseCaseTest {
    private val repository: MovieTypesRepository = mock()
    private val sut = DefaultFetchMoviesByTypeUseCase(repository)

    @Test
    fun `EXPECT valid object WHEN repo returns valid response`() = runTest {
        whenever(repository.fetchMoviesByType(TYPE)).thenReturn(BMResult.Success(MOVIE_TYPE))

        val actual = sut.invoke(TYPE).asSuccess().data

        Assert.assertEquals(MOVIE_TYPE, actual)
    }

    @Test
    fun `EXPECT error returned WHEN repo returns error`() = runTest {
        val error = BMResult.Error(Unit)
        whenever(repository.fetchMoviesByType(TYPE)).thenReturn(error)

        val actual = sut.invoke(TYPE).asError()

        Assert.assertEquals(error, actual)
    }

    private companion object {
        const val TYPE = "type"
        val MOVIE_TYPE = MoviesType(results = emptyList())
    }
}
