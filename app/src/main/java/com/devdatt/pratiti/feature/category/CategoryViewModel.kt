package com.devdatt.pratiti.feature.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devdatt.pratiti.data.model.Track
import com.devdatt.pratiti.data.repository.TrackRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.devdatt.pratiti.domain.model.Category


class CategoryViewModel : ViewModel() {

    private val repository = TrackRepository()

    var tracks by mutableStateOf<List<Track>>(emptyList())
        private set

    fun loadTracks(category: Category) {
        tracks = repository.getTracksByCategory(category)
    }
}