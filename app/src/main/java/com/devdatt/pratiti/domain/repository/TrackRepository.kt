package com.devdatt.pratiti.domain.repository

import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track

/**
 * Contract for loading categories and tracks.
 *
 * UI and ViewModels depend only on this interface so the data source
 * (local `assets/catalog.json` today, Cloudflare later) can change safely.
 */
interface TrackRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getCategoryById(categoryId: String): Category?
    suspend fun getTracksByCategory(categoryId: String): List<Track>
    suspend fun getTrackById(id: Int): Track?
}
