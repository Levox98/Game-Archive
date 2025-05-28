package com.levox.game_archive.di

import com.levox.game_archive.domain.AuthAsDeveloperUseCase
import com.levox.game_archive.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    fun provideAuthAsDeveloperUseCase(authRepository: AuthRepository): AuthAsDeveloperUseCase {
        return AuthAsDeveloperUseCase(authRepository)
    }
}