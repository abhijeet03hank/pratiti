package com.devdatt.pratiti.feature.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.devdatt.pratiti.core.navigation.Routes
import com.devdatt.pratiti.feature.category.component.TrackItem

/**
 * Lists tracks for one category.
 * [categoryId] is also available to [CategoryViewModel] via navigation SavedStateHandle.
 */
@Composable
fun CategoryScreen(
    categoryId: String,
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    when (val state = viewModel.uiState) {
        is CategoryUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CategoryUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message)
            }
        }

        is CategoryUiState.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.category?.displayName ?: categoryId,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )

                if (state.tracks.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No tracks in this category yet")
                    }
                } else {
                    LazyColumn {
                        items(state.tracks, key = { it.id }) { track ->
                            TrackItem(
                                track = track,
                                onClick = {
                                    navController.navigate(Routes.player(track.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
