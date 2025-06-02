package com.levox.game_archive.data

import com.levox.game_archive.BuildConfig
import com.levox.game_archive.data.network.LoggingInterceptor
import com.levox.game_archive.data.network.LoggingInterceptorOkHttpClient
import com.levox.game_archive.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @LoggingInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideLoggingOkHttpClient(
        loggingInterceptor: LoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("auth")
    fun provideRetrofitAuth(@LoggingInterceptorOkHttpClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("main")
    fun provideRetrofit(@LoggingInterceptorOkHttpClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(@Named("auth") retrofit: Retrofit): AuthService =
        retrofit.create<AuthService>(AuthService::class.java)
}