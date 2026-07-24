package com.devdatt.pratiti.core.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.devdatt.pratiti.core.util.getRawResId

/**
 * App-wide wrapper around a single [ExoPlayer] instance.
 *
 * Provided as a Hilt `@Singleton` so Home → Category → Player share the same playback session.
 * Do **not** create a new [PlayerManager] inside Compose screens.
 *
 * Call [release] only when the app process is shutting down (not when leaving PlayerScreen).
 */
class PlayerManager(private val context: Context) {

    private val player = ExoPlayer.Builder(context).build()
    private var currentResId: Int? = null

    /**
     * Plays a track stored under `res/raw` by file name (e.g. `"sakal_pooja_avsar.mp3"`).
     *
     * @return `true` if the raw resource was found and playback started / resumed.
     */
    fun playRawFile(fileName: String): Boolean {
        val resId = getRawResId(context, fileName)
        if (resId == 0) return false
        playRaw(resId)
        return true
    }

    /**
     * Starts or resumes playback for a raw resource id.
     * If [resId] is already loaded, only resumes; otherwise prepares a new [MediaItem].
     */
    fun playRaw(resId: Int) {
        if (currentResId == resId) {
            player.play()
            return
        }

        currentResId = resId
        val uri = "android.resource://${context.packageName}/$resId"
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
    }

    /** Pauses playback without unloading the current media. */
    fun pause() {
        player.pause()
    }

    fun isPlaying(): Boolean = player.isPlaying

    /** Releases ExoPlayer. Only call from app-level teardown. */
    fun release() {
        player.release()
    }

    fun getPlayer(): ExoPlayer = player
}
