package com.banquemisr.pdpui.presentation.viewmodel

import androidx.lifecycle.*
import com.banquemisr.pdpcomponent.domain.model.MovieDetails
import com.banquemisr.pdpcomponent.domain.usecase.FetchMovieDetails
import com.banquemisr.viewmodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.*

@HiltViewModel
internal class PdpViewModel @Inject constructor(
    private val fetchMovieDetails: FetchMovieDetails,
    private val stateDelegate: StateDelegate<State> = StateDelegate()
) : ViewModel(),
    DefaultLifecycleObserver,
    StateViewModel<PdpViewModel.State> by stateDelegate {

    init {
        stateDelegate.setDefaultState(State.Idle)
    }

    fun fetchMovieDetails(id: String) {
        stateDelegate.updateState { State.Loading }
        viewModelScope.launch {
            fetchMovieDetails.invoke(id).fold(
                success = { movie ->
                    stateDelegate.updateState { State.Content(movie) }
                },
                error = {
                    stateDelegate.updateState { State.Error }
                }
            )
        }
    }

    sealed interface State {
        data object Idle : State
        data object Loading : State
        data object Error : State
        data class Content(
            val movie: MovieDetails
        ) : State
    }
}
