package com.levox.game_archive.auth

import androidx.lifecycle.viewModelScope
import com.levox.game_archive.BuildConfig
import com.levox.game_archive.base.BaseViewModel
import com.levox.game_archive.domain.AuthAsDeveloperUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authAsDeveloperUseCase: AuthAsDeveloperUseCase
) : BaseViewModel() {

    fun authAsDeveloper() {
        if (!BuildConfig.DEBUG) throw IllegalStateException("Developer auth is not available in release builds")

        updateState(State.Loading)

        viewModelScope.launch {
            authAsDeveloperUseCase()
            updateState(State.Default)
        }
    }
}