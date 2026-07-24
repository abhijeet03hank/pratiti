package com.devdatt.pratiti.domain.usecase

import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.domain.repository.TrackRepository
import javax.inject.Inject

/** Returns all tracks that belong to the given category. */
class GetTracksByCategoryUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(categoryId: String): List<Track> =
        repository.getTracksByCategory(categoryId)
}
