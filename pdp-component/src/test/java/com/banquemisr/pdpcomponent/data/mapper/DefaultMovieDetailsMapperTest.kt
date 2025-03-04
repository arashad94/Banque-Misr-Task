package com.banquemisr.pdpcomponent.data.mapper
import com.banquemisr.pdpcomponent.data.model.MovieDetailsDto
import com.banquemisr.pdpcomponent.data.model.MovieGenre
import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import org.junit.*

class DefaultMovieDetailsMapperTest {
    private val sut = DefaultMovieDetailsMapper()

    @Test
    fun `EXPECT correctly mapped entity WHEN dto with valid parameters`() {
        val entity = sut.transform(MOVIES_DETAILS_DTO)

        Assert.assertEquals(EXPECTED_MOVIES_DETAILS, entity)
    }

    private companion object {
        val MOVIES_DETAILS_DTO = MovieDetailsDto(
            id = 12,
            title = "title",
            posterPath = "poster",
            overview = "overview",
            releaseDate = "1-1-2020",
            voteAverage = 1.5,
            status = "status",
            genres = listOf(MovieGenre(1, "genre"))
        )
        val EXPECTED_MOVIES_DETAILS = MovieDetails(
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
