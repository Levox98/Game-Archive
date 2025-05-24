package com.levox.game_archive.data.service

import com.levox.game_archive.BuildConfig
import com.levox.game_archive.data.model.AuthTokenResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("token")
    suspend fun getAuthToken(
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("grant_type") grantType: String = "client_credentials"
    ): AuthTokenResponse
}