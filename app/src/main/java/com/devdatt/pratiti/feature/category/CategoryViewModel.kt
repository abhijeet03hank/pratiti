package com.devdatt.pratiti.feature.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.domain.usecase.GetCategoryByIdUseCase
import com.devdatt.pratiti.domain.usecase.GetTracksByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/** UI state for the category track list screen. */
sealed interface CategoryUiState {
    data object Loading : CategoryUiState
    data class Success(
        val category: Category?,
        val tracks: List<Track>
    ) : CategoryUiState

    data class Error(val message: String) : CategoryUiState
}

/**
 * Loads one category and its tracks.
 *
 * [categoryId] is read from the navigation back stack (`category/{categoryId}`)
 * through [SavedStateHandle], so it survives process recreation.
 */
@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoryById: GetCategoryByIdUseCase,
    private val getTracksByCategory: GetTracksByCategoryUseCase
) : ViewModel() {

    private val categoryId: String = checkNotNull(savedStateHandle["categoryId"])

    var uiState by mutableStateOf<CategoryUiState>(CategoryUiState.Loading)
        private set

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            uiState = CategoryUiState.Loading
            uiState = try {
                CategoryUiState.Success(
                    category = getCategoryById(categoryId),
                    tracks = getTracksByCategory(categoryId)
                )
            } catch (e: Exception) {
                CategoryUiState.Error(e.message ?: "Failed to load tracks")
            }
        }
    }
}
