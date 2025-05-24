package com.levox.game_archive.domain.repository

interface AuthRepository {

    suspend fun getDevAuthToken()
}