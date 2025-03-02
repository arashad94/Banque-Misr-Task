package com.banquemisr.homecomponent.data.api

import com.banquemisr.homecomponent.data.model.*
import com.banquemisr.networkutils.createService
import com.denisbrandi.netmock.*
import com.denisbrandi.netmock.resources.readFromResources
import com.denisbrandi.netmock.server.NetMockServerRule
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*
import retrofit2.HttpException

class MoviesByTypeApiServiceTest {
    @get:Rule
    val netMock = NetMockServerRule()
    private val sut: MoviesByTypeApiService = createService(netMock.interceptor, URL)

    @Test
    fun `EXPECT valid request and parsing WHEN request is successful`() = runTest {
        netMock.addMock(EXPECTED_REQUEST, NetMockResponse(code = 200, body = RESPONSE_BODY))

        val result = sut.getMovieByType(type = MOVIES_TYPE)

        assertEquals(NOW_PLAYING_DTO, result)
    }

    @Test
    fun `EXPECT valid request with error response WHEN request is not successful`() = runTest {
        netMock.addMock(EXPECTED_REQUEST, NetMockResponse(code = 500))

        var error: Throwable? = null
        try {
            sut.getMovieByType(type = MOVIES_TYPE)
        } catch (t: Throwable) {
            error = t
        }

        assertTrue(error is HttpException)
        assertEquals(500, (error as HttpException).code())
    }

    private companion object {
        const val URL = "https://api.themoviedb.org/3/"
        const val MOVIES_TYPE = "now_playing"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MzlhYTRkNDkxNzNlZTE0YzNmYTAzOTExMmNhNjFlNSIsIm5iZiI6MTc0MDg3OTE3OC40ODgsInN1YiI6IjY3YzNiNTRhODlhZmZmOWUzMjRlMGFjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xA-hlcxzhrmjDH-Tm195gutA0-n3IE6dmxgA3aB5s9I"
        val EXPECTED_REQUEST = NetMockRequest(
            method = Method.Get,
            requestUrl = "${URL}movie/$MOVIES_TYPE",
            mandatoryHeaders = mapOf(
                AUTHORIZATION_HEADER to TOKEN
            )
        )
        val RESPONSE_BODY = readFromResources("responses/getMovieByType.json")
        val NOW_PLAYING_DTO = MoviesTypeDto(
            results = listOf(
                MovieDto(
                    adult = false,
                    id = 950396,
                    title = "The Gorge",
                    posterPath = "/xj63YtNo9NlnspYpLR29A9fuxBb.jpg",
                    overview = "Two highly trained operatives grow close from a distance after being sent to guard opposite sides of a mysterious gorge. When an evil below emerges, they must work together to survive what lies within.",
                    releaseDate = "2025-02-13"
                ),
                MovieDto(
                    adult = false,
                    id = 1126166,
                    title = "Flight Risk",
                    posterPath = "/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg",
                    overview = "A U.S. Marshal escorts a government witness to trial after he's accused of getting involved with a mob boss, only to discover that the pilot who is transporting them is also a hitman sent to assassinate the informant. After they subdue him, they're forced to fly together after discovering that there are others attempting to eliminate them.",
                    releaseDate = "2025-01-22"
                )
            )
        )
    }
}
