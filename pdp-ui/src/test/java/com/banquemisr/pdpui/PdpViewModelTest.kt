package com.banquemisr.pdpui

import com.banquemisr.bmflows.*
import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.pdpcomponent.domain.usecase.FetchMovieDetails
import com.banquemisr.pdpui.presentation.viewmodel.PdpViewModel
import com.banquemisr.shared.BMResult
import com.banquemisr.viewmodel.StateDelegate
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertTrue
import org.mockito.kotlin.*

class PdpViewModelTest {
    private val fetchMoviesByType: FetchMovieDetails = mock()
    private val stateDelegate: StateDelegate<PdpViewModel.State> = StateDelegate()
    private lateinit var sut: PdpViewModel
    private lateinit var testObserver: TestObserver<PdpViewModel.State>

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = PdpViewModel(fetchMoviesByType, stateDelegate)
        testObserver = sut.state.test()
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }

    @Test
    fun `EXPECT initial state is Idle WHEN initialized`() = runTest {
        // Assert initial state
        val initialState = sut.state.value
        assertTrue(initialState is PdpViewModel.State.Idle)
    }

    @Test
    fun `EXPECT Content State WHEN fetchMovieDetails succeed`() = runTest {
        whenever(fetchMoviesByType.invoke(MOVIE_ID)).thenReturn(BMResult.Success(MOVIE_DETAILS))

        sut.fetchMovieDetails(MOVIE_ID)

        testObserver.assertValueHistory(
            PdpViewModel.State.Idle,
            PdpViewModel.State.Loading,
            PdpViewModel.State.Content(MOVIE_DETAILS)
        )
    }

    @Test
    fun `EXPECT Error State WHEN fetchMovieDetails fails`() = runTest {
        whenever(fetchMoviesByType.invoke(MOVIE_ID)).thenReturn(BMResult.Error(Unit))

        sut.fetchMovieDetails(MOVIE_ID)

        testObserver.assertValueHistory(
            PdpViewModel.State.Idle,
            PdpViewModel.State.Loading,
            PdpViewModel.State.Error
        )
    }

    private companion object {
        const val MOVIE_ID = "1"
        val MOVIE_DETAILS = MovieDetails(
            id = 1,
            title = "Title",
            posterPath = "Poster",
            overview = "Overview",
            releaseDate = "Release date",
            voteAverage = 3.5,
            status = "status",
            genres = listOf("genres")
        )
    }
}
