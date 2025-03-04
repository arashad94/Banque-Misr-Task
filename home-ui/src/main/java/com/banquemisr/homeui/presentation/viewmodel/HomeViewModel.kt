package com.banquemisr.homeui.presentation.viewmodel

import androidx.lifecycle.*
import com.banquemisr.homecomponent.domain.model.Movie
import com.banquemisr.homecomponent.domain.usecase.FetchMoviesByType
import com.banquemisr.homeui.data.TabInfo
import com.banquemisr.homeui.presentation.viewmodel.HomeViewModel.DisplayState.*
import com.banquemisr.viewmodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val fetchMoviesByType: FetchMoviesByType,
    private val stateDelegate: StateDelegate<State> = StateDelegate()
) : ViewModel(),
    DefaultLifecycleObserver,
    StateViewModel<HomeViewModel.State> by stateDelegate {

    init {
        stateDelegate.setDefaultState(State.Idle)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        fetchMovies()
    }

    fun fetchMovies(index: Int = 0, type: String = "now_playing") {
        stateDelegate.updateState { State.Content(displayState = Loading) }
        viewModelScope.launch {
            fetchMoviesByType.invoke(type).fold(
                success = { type ->
                    val newState = State.Content(index = index, displayState = ContentState(type.results))
                    stateDelegate.forceUpdateState(newState)
                },
                error = {
                    stateDelegate.updateState { State.Content(displayState = Error) }
                }
            )
        }
    }

    fun onTabSelected(index: Int) {
        stateDelegate.onState<State.Content> { state ->
            if (index != state.index) {
                val tab = state.tabsList[index]
                stateDelegate.updateState { state.copy(index = index) }
                fetchMovies(index, tab.key)
            }
        }
    }

    sealed interface State {
        data object Idle : State
        data class Content(
            val tabsList: List<TabInfo> = listOf(
                // This should be also coming from an API but out of scope
                TabInfo("Now Playing", "now_playing"),
                TabInfo("Popular", "popular"),
                TabInfo("Upcoming", "upcoming"),
            ),
            val index: Int = 0,
            val displayState: DisplayState
        ) : State
    }

    sealed interface DisplayState {
        data object Loading : DisplayState
        data object Error : DisplayState
        data class ContentState(
            val movies: List<Movie>
        ) : DisplayState
    }
}
