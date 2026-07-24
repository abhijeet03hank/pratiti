package com.devdatt.pratiti.domain.model

/**
 * A single playable track in the catalog.
 *
 * [fileName] currently maps to a file under `res/raw` (without needing the extension for lookup).
 * Later this can become a remote URL while keeping the same domain model shape.
 */
data class Track(
    val id: Int,
    val title: String,
    val categoryId: String,
    val fileName: String
)
