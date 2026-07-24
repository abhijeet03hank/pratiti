package com.devdatt.pratiti.data.repository

import com.devdatt.pratiti.data.local.CatalogLocalDataSource
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.domain.repository.TrackRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default [TrackRepository] implementation.
 * Currently delegates everything to [CatalogLocalDataSource] (local assets).
 */
@Singleton
class TrackRepositoryImpl @Inject constructor(
    private val localDataSource: CatalogLocalDataSource
) : TrackRepository {

    override suspend fun getCategories(): List<Category> =
        localDataSource.getCategories()

    override suspend fun getCategoryById(categoryId: String): Category? =
        localDataSource.getCategoryById(categoryId)

    override suspend fun getTracksByCategory(categoryId: String): List<Track> =
        localDataSource.getTracksByCategory(categoryId)

    override suspend fun getTrackById(id: Int): Track? =
        localDataSource.getTrackById(id)
}
