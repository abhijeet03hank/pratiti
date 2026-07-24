package com.devdatt.pratiti.data.model

import kotlinx.serialization.Serializable

/**
 * JSON shape of `assets/catalog.json`.
 * Mapped to domain models via [com.devdatt.pratiti.data.mapper.toDomain].
 */
@Serializable
data class CatalogDto(
    val categories: List<CategoryDto>,
    val tracks: List<TrackDto>
)

@Serializable
data class CategoryDto(
    val id: String,
    val name: String,
    val displayName: String
)

@Serializable
data class TrackDto(
    val id: Int,
    val title: String,
    val categoryId: String,
    val fileName: String
)
