package com.devdatt.pratiti.feature.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devdatt.pratiti.domain.mapper.toCategory
import com.devdatt.pratiti.feature.category.component.TrackItem

@Composable
fun CategoryScreen(
    name: String,
    navController: NavController,
    viewModel: CategoryViewModel = viewModel()
) {

    // 🔥 Load data once
    LaunchedEffect(Unit) {
        viewModel.loadTracks(name.toCategory())
    }

    val tracks = viewModel.tracks   // 👈 get data from VM

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(tracks) { track ->
                TrackItem(
                    track = track,
                    onClick = {
                        navController.navigate("player/${track.id}")
                    }
                )
            }
        }
    }

}