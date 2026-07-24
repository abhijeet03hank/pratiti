package com.devdatt.pratiti.di

import com.devdatt.pratiti.data.repository.TrackRepositoryImpl
import com.devdatt.pratiti.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds domain repository interfaces to their data-layer implementations.
 * When Cloudflare is added, only the implementation (or which impl is bound) needs to change.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTrackRepository(
        impl: TrackRepositoryImpl
    ): TrackRepository
}
