package com.devdatt.pratiti.domain.usecase

import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.repository.TrackRepository
import javax.inject.Inject

/** Looks up one category by its stable [categoryId] (from navigation). */
class GetCategoryByIdUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(categoryId: String): Category? =
        repository.getCategoryById(categoryId)
}
