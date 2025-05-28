package com.levox.game_archive.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel() : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Default)
    val state: StateFlow<State> = _state.asStateFlow()

    fun updateState(state: State) {
        _state.value = state
    }

    sealed interface State {
        object Default : State
        object Loading : State
        data class Error(val message: String) : State
    }
}