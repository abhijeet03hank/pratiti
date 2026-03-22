package com.devdatt.pratiti.data.repository

import com.devdatt.pratiti.data.model.Track
import com.devdatt.pratiti.domain.model.Category


class TrackRepository {

    private val stavanTracks = listOf(
        Track(1, "Sakal Pooja Avsar", "sakal_pooja_avsar.mp3"),
        Track(2, "Sayankal Pooja Avsar", "sayankal_pooja_avsar.mp3")
    )

    // If you add more categories later, extend this map
    private val categoryMap = mapOf(
        Category.STAVAN to stavanTracks
    )

    fun getTracksByCategory(category: Category): List<Track> {
        return categoryMap[category] ?: emptyList()
    }

    fun getTrackById(id: Int): Track? {
        return categoryMap.values.flatten().find { it.id == id }
    }
}