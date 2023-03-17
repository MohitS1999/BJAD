package com.example.bjad.repository.musicRepository

import com.example.bjad.Model.MusicData
import com.example.bjad.util.UiState

interface MusicRepository {

    suspend fun getMusicSongs(result:(UiState.Success<ArrayList<MusicData>>) -> Unit)

}