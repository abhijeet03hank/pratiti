package com.devdatt.pratiti.data.local

import android.content.Context
import com.devdatt.pratiti.data.mapper.toDomain
import com.devdatt.pratiti.data.model.CatalogDto
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Reads `assets/catalog.json` once and caches it in memory.
 *
 * All category/track queries go through this class today.
 * A future Cloudflare data source can sit beside this without changing UI code.
 */
@Singleton
class CatalogLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json
) {
    @Volatile
    private var cachedCatalog: CatalogDto? = null

    /**
     * Loads the catalog from assets on a background thread, or returns the cached copy.
     * `@Volatile` + check ensures concurrent callers share one in-memory catalog.
     */
    private suspend fun loadCatalog(): CatalogDto = withContext(Dispatchers.IO) {
        cachedCatalog?.let { return@withContext it }
        val text = context.assets.open(CATALOG_ASSET).bufferedReader().use { it.readText() }
        json.decodeFromString(CatalogDto.serializer(), text).also { cachedCatalog = it }
    }

    suspend fun getCategories(): List<Category> =
        loadCatalog().categories.map { it.toDomain() }

    suspend fun getCategoryById(categoryId: String): Category? =
        getCategories().find { it.id == categoryId }

    suspend fun getTracksByCategory(categoryId: String): List<Track> =
        loadCatalog().tracks
            .filter { it.categoryId == categoryId }
            .map { it.toDomain() }

    suspend fun getTrackById(id: Int): Track? =
        loadCatalog().tracks.find { it.id == id }?.toDomain()

    companion object {
        private const val CATALOG_ASSET = "catalog.json"
    }
}
