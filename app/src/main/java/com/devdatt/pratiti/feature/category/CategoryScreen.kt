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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devdatt.pratiti.data.model.Track
import com.devdatt.pratiti.feature.category.component.TrackItem

@Composable
fun CategoryScreen(name: String) {

    val tracks = when (name) {
        "Stavan" -> listOf(
            Track("Sakal Pooja Avsar", "sakal_pooja_avsar.mp3"),
            Track("Sayankal Pooja Avsar", "sayankal_pooja_avsar.mp3")
        )
        else -> emptyList()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🧭 Header
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
                        // TODO: play track later
                    }
                )
            }
        }
    }
}