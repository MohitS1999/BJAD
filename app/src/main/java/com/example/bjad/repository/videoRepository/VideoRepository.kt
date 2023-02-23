package com.example.bjad.repository.videoRepository

import com.example.bjad.Model.VideoData
import com.example.bjad.util.UiState

interface VideoRepository {
    suspend fun getVideoSongs(result: (UiState.Success<ArrayList<VideoData>>) -> Unit)
}