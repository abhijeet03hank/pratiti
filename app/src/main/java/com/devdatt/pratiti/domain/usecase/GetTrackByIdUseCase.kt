package com.devdatt.pratiti.domain.usecase

import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.domain.repository.TrackRepository
import javax.inject.Inject

/** Loads a single track for the Player screen by id. */
class GetTrackByIdUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(id: Int): Track? = repository.getTrackById(id)
}
