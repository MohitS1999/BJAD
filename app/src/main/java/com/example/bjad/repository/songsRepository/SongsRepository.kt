package com.example.bjad.repository.songsRepository

import com.example.bjad.Model.MusicData
import com.example.bjad.util.UiState

interface SongsRepository {
    suspend fun getSongs(result: (UiState<ArrayList<MusicData>>) -> Unit)
    suspend fun initSong(url: String)
    fun play()
    fun pause()
    fun destroy()
    fun initiableMediaPlayerObject()
}