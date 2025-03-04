package com.banquemisr.homeui

import com.banquemisr.bmflows.*
import com.banquemisr.homecomponent.domain.model.*
import com.banquemisr.homecomponent.domain.usecase.FetchMoviesByTypeUseCase
import com.banquemisr.homeui.data.TabInfo
import com.banquemisr.homeui.presentation.viewmodel.HomeViewModel
import com.banquemisr.shared.BMResult
import com.banquemisr.viewmodel.StateDelegate
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertTrue
import org.mockito.kotlin.*

class HomeViewModelTest {

    private val fetchMoviesByTypeUseCase: FetchMoviesByTypeUseCase = mock()
    private val stateDelegate: StateDelegate<HomeViewModel.State> = StateDelegate()
    private lateinit var sut: HomeViewModel
    private lateinit var observer: TestObserver<HomeViewModel.State>

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = HomeViewModel(fetchMoviesByTypeUseCase, stateDelegate)
        observer = sut.state.test()
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }

    @Test
    fun `EXPECT initial state is Idle WHEN initialized`() = runTest {
        // Assert initial state
        val initialState = sut.state.value
        assertTrue(initialState is HomeViewModel.State.Idle)
    }

    @Test
    fun `EXPECT state is Content with movies WHEN fetchMovies is called and API succeeds`() = runTest {
        whenever(fetchMoviesByTypeUseCase(NOW_PLAYING)).thenReturn(BMResult.Success(MOVIES_TYPE))

        sut.fetchMovies(type = NOW_PLAYING)
        advanceUntilIdle()

        observer.assertValueHistory(
            HomeViewModel.State.Idle,
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Loading
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.ContentState(MOVIES)
            )
        )
    }

    @Test
    fun `EXPECT state is Content with Error WHEN fetchMovies is called and API fails`() = runTest {
        whenever(fetchMoviesByTypeUseCase.invoke(NOW_PLAYING)).thenReturn(BMResult.Error(Unit))

        sut.fetchMovies(type = NOW_PLAYING)

        observer.assertValueHistory(
            HomeViewModel.State.Idle,
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Loading
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Error
            )
        )
    }

    @Test
    fun `EXPECT state updates with new tab index and movies WHEN onTabSelected is called`() = runTest {
        whenever(fetchMoviesByTypeUseCase.invoke(NOW_PLAYING)).thenReturn(BMResult.Success(MOVIES_TYPE))
        whenever(fetchMoviesByTypeUseCase.invoke(POPULAR)).thenReturn(BMResult.Success(MOVIES_TYPE))

        sut.fetchMovies(type = NOW_PLAYING)
        sut.onTabSelected(1)

        observer.assertValueHistory(
            HomeViewModel.State.Idle,
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Loading
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.ContentState(MOVIES)
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 1,
                displayState = HomeViewModel.DisplayState.ContentState(MOVIES)
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Loading
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 1,
                displayState = HomeViewModel.DisplayState.ContentState(MOVIES)
            )
        )
    }

    @Test
    fun `EXPECT no state change WHEN onTabSelected is called with the same tab`() = runTest {
        whenever(fetchMoviesByTypeUseCase.invoke(NOW_PLAYING)).thenReturn(BMResult.Success(MOVIES_TYPE))

        sut.fetchMovies(type = NOW_PLAYING) // Fetch movies for the first tab
        sut.onTabSelected(0) // Select the same tab again

        observer.assertValueHistory(
            HomeViewModel.State.Idle,
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.Loading
            ),
            HomeViewModel.State.Content(
                tabsList = TABS_LIST,
                index = 0,
                displayState = HomeViewModel.DisplayState.ContentState(MOVIES)
            )
        )
    }

    private companion object {
        val MOVIE = Movie(
            adult = false,
            id = 1,
            title = "Movie 1",
            posterPath = "Overview 1",
            overview = "Over view",
            releaseDate = "1-1-2020"
        )
        val MOVIES = listOf(
            MOVIE.copy(id = 1),
            MOVIE.copy(id = 2)
        )
        val MOVIES_TYPE = MoviesType(MOVIES)
        val TABS_LIST: List<TabInfo> = listOf(
            // This should be also coming from an API but out of scope
            TabInfo("Now Playing", "now_playing"),
            TabInfo("Popular", "popular"),
            TabInfo("Upcoming", "upcoming"),
        )
        const val POPULAR = "popular"
        const val NOW_PLAYING = "popular"
    }
}
