package com.devdatt.pratiti.domain.usecase

import com.devdatt.pratiti.data.model.Track
import com.devdatt.pratiti.data.repository.TrackRepository

class GetTrackByIdUseCase(
    private val repository: TrackRepository
) {
    operator fun invoke(id: Int): Track? {
        return repository.getTrackById(id)
    }
}