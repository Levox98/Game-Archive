package com.levox.game_archive.data.model

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("expires_in")
    val expiresIn: Long?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("message")
    val errorMessage: String?
)
