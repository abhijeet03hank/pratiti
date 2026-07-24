package com.devdatt.pratiti.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdatt.pratiti.domain.model.Category
import com.devdatt.pratiti.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/** UI state for the Home categories grid. */
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val categories: List<Category>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

/**
 * Loads categories for the Home tab via [GetCategoriesUseCase].
 * Screens observe [uiState]; they never talk to the repository directly.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase
) : ViewModel() {

    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)
        private set

    init {
        loadCategories()
    }

    /** Reloads categories (also used as a tap-to-retry from the error UI). */
    fun loadCategories() {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                HomeUiState.Success(getCategories())
            } catch (e: Exception) {
                HomeUiState.Error(e.message ?: "Failed to load categories")
            }
        }
    }
}
