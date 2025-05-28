package com.levox.game_archive.domain.repository

import com.levox.game_archive.domain.Either
import com.levox.game_archive.domain.model.AuthToken

interface AuthRepository {

    suspend fun getDevAuthToken(): Either<AuthToken>
}