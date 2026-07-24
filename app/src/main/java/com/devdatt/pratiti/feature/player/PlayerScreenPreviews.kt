package com.devdatt.pratiti.feature.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devdatt.pratiti.domain.model.Track
import com.devdatt.pratiti.ui.theme.PratitiTheme

private val previewTrack = Track(
    id = 1,
    title = "Sakal Pooja Avsar",
    categoryId = "pooja_avsar",
    fileName = "sakal_pooja_avsar.mp3"
)

@Preview(name = "Player – playing", showBackground = true, showSystemUi = true)
@Composable
private fun PlayerScreenPlayingPreview() {
    PratitiTheme(dynamicColor = false) {
        PlayerScreenContent(
            track = previewTrack,
            isPlaying = true,
            onBack = {},
            onTogglePlayPause = {}
        )
    }
}

@Preview(name = "Player – paused", showBackground = true)
@Composable
private fun PlayerScreenPausedPreview() {
    PratitiTheme(dynamicColor = false) {
        PlayerScreenContent(
            track = previewTrack,
            isPlaying = false,
            onBack = {},
            onTogglePlayPause = {}
        )
    }
}

@Preview(name = "Player – loading track", showBackground = true)
@Composable
private fun PlayerScreenLoadingPreview() {
    PratitiTheme(dynamicColor = false) {
        PlayerScreenContent(
            track = null,
            isPlaying = false,
            onBack = {},
            onTogglePlayPause = {}
        )
    }
}
