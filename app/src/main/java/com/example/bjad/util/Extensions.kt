package com.example.bjad.util

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bjad.ui.audio.MusicPlayer
import com.example.bjad.ui.audio.PlayerViewModel
import java.util.concurrent.TimeUnit

private const val TAG = "Extensions"
fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()


// Audio related
fun formatDuration(duration: Long):String{
        val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)) -
                minutes* TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)
        return String.format("%02d:%02d",minutes,seconds)

}

fun getBitmapFromUrl(url: String,context: Context): Bitmap? {
        return try {
                val options = RequestOptions()
                        .override(200, 200)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .apply(options)
                        .submit()

                        .get()

        } catch (e: Exception) {
                null
        }
}

fun setSongPosition(increment: Boolean){
        if (!MusicPlayer.repeat){
                if (increment){
                        if(MusicPlayer.musicList.size-1 == MusicPlayer.position) MusicPlayer.position = 0
                        else ++MusicPlayer.position
                }else{
                        if (MusicPlayer.position == 0) MusicPlayer.position = MusicPlayer.musicList.size - 1
                        else --MusicPlayer.position
                }
        }

}

fun releaseMusicMemory(){
        Log.d(TAG, "releaseMusicMemory: ")
        PlayerViewModel.musicService!!.audioManager.abandonAudioFocus(PlayerViewModel.musicService)
        PlayerViewModel.musicService!!.stopForeground(true)
        PlayerViewModel.musicService!!.mediaPlayer!!.release()
        PlayerViewModel.musicService!!.mediaPlayer = null
        PlayerViewModel.musicService = null
}

