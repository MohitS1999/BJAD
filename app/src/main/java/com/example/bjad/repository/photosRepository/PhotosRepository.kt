package com.example.bjad.repository.photosRepository

import com.example.bjad.util.UiState

interface PhotosRepository {
    suspend fun getPhotos(result: (UiState<ArrayList<String>>) -> Unit)
}