package com.devdatt.pratiti.di

import android.content.Context
import com.devdatt.pratiti.core.player.PlayerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Provides app-scoped utilities that are not bound via `@Binds`
 * (JSON parser for the local catalog, shared audio player).
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun providePlayerManager(
        @ApplicationContext context: Context
    ): PlayerManager = PlayerManager(context)
}
