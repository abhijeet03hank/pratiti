package com.devdatt.pratiti.domain.model

/**
 * A music category shown on the Home grid (e.g. Aarti, Bhajan).
 *
 * [id] is stable and used in navigation; [displayName] is what the user sees.
 */
data class Category(
    val id: String,
    val name: String,
    val displayName: String
)
