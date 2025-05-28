package com.levox.game_archive.domain

import com.levox.game_archive.domain.repository.AuthRepository

class AuthAsDeveloperUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.getDevAuthToken()
}