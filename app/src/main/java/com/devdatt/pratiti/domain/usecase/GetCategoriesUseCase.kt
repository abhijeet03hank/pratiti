package com.devdatt.pratiti.domain.usecase

import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.repository.TrackRepository
import javax.inject.Inject

/** Returns every category for the Home screen grid. */
class GetCategoriesUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(): List<Category> = repository.getCategories()
}
