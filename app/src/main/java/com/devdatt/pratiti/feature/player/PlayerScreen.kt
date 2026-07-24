package com.devdatt.pratiti.feature.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Full-screen player UI.
 *
 * Playback is owned by [PlayerViewModel] (shared [com.devdatt.pratiti.core.player.PlayerManager]).
 * Seek bar / duration will be added in a later phase when wired to ExoPlayer position.
 */
@Composable
fun PlayerScreen(
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val track = viewModel.track
    val isPlaying = viewModel.isPlaying

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "← Back",
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = track?.title.orEmpty(),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = viewModel::togglePlayPause) {
                Text(if (isPlaying) "Pause" else "Play")
            }
        }
    }
}
