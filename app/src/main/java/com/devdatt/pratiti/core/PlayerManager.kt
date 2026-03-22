package com.devdatt.pratiti.core
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class PlayerManager(private val context: Context) {

    private val player = ExoPlayer.Builder(context).build()
    private var currentResId: Int? = null
    fun playRaw(resId: Int) {
        // 👉 If same track, just resume
        if (currentResId == resId) {
            player.play()
            return
        }

        // 👉 New track → load fresh
        currentResId = resId

        val uri = "android.resource://${context.packageName}/$resId"
        val mediaItem = MediaItem.fromUri(uri)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun pause() {
        player.pause()
    }
    fun isPlaying(): Boolean = player.isPlaying

    fun release() {
        player.release()
    }

    fun getPlayer(): ExoPlayer = player
}