package com.devdatt.pratiti.feature.player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdatt.pratiti.core.player.PlayerManager
import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.domain.usecase.GetTrackByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Loads the selected track and controls playback through the shared [PlayerManager].
 *
 * [trackId] comes from the navigation route (`player/{trackId}`) via [SavedStateHandle].
 */
@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTrackById: GetTrackByIdUseCase,
    private val playerManager: PlayerManager
) : ViewModel() {

    private val trackId: Int = checkNotNull(savedStateHandle["trackId"])

    var track by mutableStateOf<Track?>(null)
        private set

    var isPlaying by mutableStateOf(false)
        private set

    init {
        loadAndPlay()
    }

    /**
     * Loads track metadata, then starts playback if a matching `res/raw` file exists.
     */
    fun loadAndPlay() {
        viewModelScope.launch {
            val loaded = getTrackById(trackId)
            track = loaded
            isPlaying = loaded?.let { playerManager.playRawFile(it.fileName) } == true
        }
    }

    /** Toggles between pause and resume / play for the current track. */
    fun togglePlayPause() {
        val current = track ?: return
        if (isPlaying) {
            playerManager.pause()
            isPlaying = false
        } else {
            isPlaying = playerManager.playRawFile(current.fileName)
        }
    }
}
