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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devdatt.pratiti.core.PlayerManager
import com.devdatt.pratiti.core.util.getRawResId

@Composable
fun PlayerScreen(trackId: Int, viewModel: PlayerViewModel = viewModel()) {

    val context = LocalContext.current
    val playerManager = remember { PlayerManager(context) }
    var isPlaying by remember { mutableStateOf(false) }

    LaunchedEffect(trackId) {
        viewModel.loadTrack(trackId)
    }

    val track = viewModel.track

    LaunchedEffect(track) {
        track?.let {
            val resId = getRawResId(context, it.fileName)
            if (resId != 0) {
                playerManager.playRaw(resId)
                isPlaying = true   // 👈 IMPORTANT
            }
        }
    }

    val resId = track?.let {
        getRawResId(context, it.fileName)
    } ?: 0


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔙 Back
        Text(
            text = "← Back",
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { /* handle back later */ }
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🎵 Track Title
        Text(
            text = track?.title ?: "",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))

        // ⏱ Seekbar (dummy for now)
        Slider(
            value = 0.3f,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        // Time Row
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("00:30")
            Text("02:00")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // ⏮ ▶ ⏭ Controls
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (isPlaying) {
                        playerManager.pause()
                        isPlaying = false
                    } else {
                        if (resId != 0) {
                            playerManager.playRaw(resId)
                            isPlaying = true
                        }
                    }
                }
            ) {
                Text(if (isPlaying) "Pause" else "Play")
            }
        }
    }
}