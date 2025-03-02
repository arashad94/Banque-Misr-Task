package com.banquemisr.homecomponent.data.mapper

import com.banquemisr.homecomponent.data.model.*
import com.banquemisr.homecomponent.domain.model.*
import org.junit.*

class DefaultMoviesByTypeMapperTest {
    private val sut = DefaultMoviesByTypeMapper()

    @Test
    fun `EXPECT correctly mapped entity WHEN dto with valid parameters`() {
        val entity = sut.transform(MOVIES_TYPE_DTO)

        Assert.assertEquals(EXPECTED_MOVIES_TYPE_DTO, entity)
    }

    private companion object {
        val MOVIES_TYPE_DTO = MoviesTypeDto(
            results = listOf(
                MovieDto(
                    adult = false,
                    id = 1,
                    title = "title",
                    posterPath = "path",
                    overview = "overview",
                    releaseDate = "date"
                )
            )
        )
        val EXPECTED_MOVIES_TYPE_DTO = MoviesType(
            results = listOf(
                Movie(
                    adult = false,
                    id = 1,
                    title = "title",
                    posterPath = "path",
                    overview = "overview",
                    releaseDate = "date"
                )
            )
        )
    }
}
