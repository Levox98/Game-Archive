package com.levox.game_archive.domain.model

data class AuthToken(
    val token: String,
    val expiresIn: Long,
    val tokenType: String,
)