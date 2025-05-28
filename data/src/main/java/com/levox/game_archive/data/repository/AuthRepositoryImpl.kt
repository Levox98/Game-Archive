package com.levox.game_archive.data.repository

import com.levox.game_archive.data.AppCache
import com.levox.game_archive.data.service.AuthService
import com.levox.game_archive.domain.Either
import com.levox.game_archive.domain.model.AuthToken
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

    override suspend fun getDevAuthToken(): Either<AuthToken> {
        val result = withContext(Dispatchers.IO) {
            authService.getAuthToken()
        }

        if (result.status != null) return Either.Error(result.errorMessage ?: "Unknown error")

        appCache.authToken = result.accessToken

        return Either.Success(
            AuthToken(
                token = result.accessToken!!,
                expiresIn = result.expiresIn!!,
                tokenType = result.tokenType!!
            )
        )
    }
}