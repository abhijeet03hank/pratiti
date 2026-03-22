package com.devdatt.pratiti.feature.player

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devdatt.pratiti.data.model.Track
import com.devdatt.pratiti.data.repository.TrackRepository
import com.devdatt.pratiti.domain.usecase.GetTrackByIdUseCase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PlayerViewModel : ViewModel() {

    private val repository = TrackRepository()
    private val getTrackById = GetTrackByIdUseCase(repository)

    var track by mutableStateOf<Track?>(null)
        private set

    fun loadTrack(id: Int) {
        track = getTrackById(id)
    }
}