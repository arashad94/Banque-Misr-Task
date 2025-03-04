package com.banquemisr.viewmodel

import javax.inject.Inject
import kotlinx.coroutines.flow.*

interface StateViewModel<State> {
    val state: StateFlow<State>
}

class StateDelegate<State> @Inject constructor() : StateViewModel<State> {
    override val state: StateFlow<State>
        get() {
            return _state.asStateFlow()
        }
    private lateinit var _state: MutableStateFlow<State>

    fun setDefaultState(state: State) {
        _state = MutableStateFlow(state)
    }

    fun updateState(block: (State) -> State) {
        _state.update { block(it) }
    }

    suspend fun forceUpdateState(state: State) {
        _state.value = state
        _state.emit(state)
    }

    inline fun <reified SubState : State> onState(block: (SubState) -> Unit) {
        val currentState = state.value
        if (currentState is SubState) { block(currentState) }
    }
}
