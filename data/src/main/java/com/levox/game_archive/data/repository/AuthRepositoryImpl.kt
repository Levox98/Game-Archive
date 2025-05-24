package com.levox.game_archive.data.repository

import com.levox.game_archive.data.AppCache
import com.levox.game_archive.data.service.AuthService
import com.levox.game_archive.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val appCache: AppCache
) : AuthRepository {

    override suspend fun getDevAuthToken() {
        val result = withContext(Dispatchers.IO) {
            authService.getAuthToken()
        }
        appCache.authToken = result.accessToken
    }
}