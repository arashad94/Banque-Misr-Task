package com.banquemisr.pdpcomponent.data.api

import com.banquemisr.networkutils.createService
import com.banquemisr.pdpcomponent.data.model.MovieDetailsDto
import com.banquemisr.pdpcomponent.data.model.MovieGenre
import com.denisbrandi.netmock.*
import com.denisbrandi.netmock.resources.readFromResources
import com.denisbrandi.netmock.server.NetMockServerRule
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*
import retrofit2.HttpException

class MovieDetailsApiServiceTest {
    @get:Rule
    val netMock = NetMockServerRule()
    private val sut: MovieDetailsApiService = createService(netMock.interceptor, URL)

    @Test
    fun `EXPECT valid request and parsing WHEN request is successful`() = runTest {
        netMock.addMock(EXPECTED_REQUEST, NetMockResponse(code = 200, body = RESPONSE_BODY))

        val result = sut.fetchMovieDetails(id = MOVIE_ID)

        assertEquals(MOVIE_DETAILS_DTO, result)
    }

    @Test
    fun `EXPECT valid request with error response WHEN request is not successful`() = runTest {
        netMock.addMock(EXPECTED_REQUEST, NetMockResponse(code = 500))

        var error: Throwable? = null
        try {
            sut.fetchMovieDetails(id = MOVIE_ID)
        } catch (t: Throwable) {
            error = t
        }

        assertTrue(error is HttpException)
        assertEquals(500, (error as HttpException).code())
    }

    private companion object {
        const val URL = "https://api.themoviedb.org/3/"
        const val MOVIE_ID = "12312"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MzlhYTRkNDkxNzNlZTE0YzNmYTAzOTExMmNhNjFlNSIsIm5iZiI6MTc0MDg3OTE3OC40ODgsInN1YiI6IjY3YzNiNTRhODlhZmZmOWUzMjRlMGFjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xA-hlcxzhrmjDH-Tm195gutA0-n3IE6dmxgA3aB5s9I"
        val EXPECTED_REQUEST = NetMockRequest(
            method = Method.Get,
            requestUrl = "${URL}movie/$MOVIE_ID",
            mandatoryHeaders = mapOf(
                AUTHORIZATION_HEADER to TOKEN
            )
        )
        val RESPONSE_BODY = readFromResources("responses/getMovieDetails.json")
        val MOVIE_DETAILS_DTO = MovieDetailsDto(
            id = 950396,
            title = "The Gorge",
            posterPath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
            overview = "Two highly trained operatives grow close from a distance after being sent to guard opposite sides of a mysterious gorge. When an evil below emerges, they must work together to survive what lies within.",
            releaseDate = "2025-02-13",
            voteAverage = 7.785,
            status = "Released",
            genres = listOf(
                MovieGenre(id = 10749, name = "Romance"),
                MovieGenre(id = 878, name = "Science Fiction"),
                MovieGenre(id = 53, name = "Thriller")
            )
        )
    }
}
