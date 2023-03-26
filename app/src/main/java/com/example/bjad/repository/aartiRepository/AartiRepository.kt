package com.example.bjad.repository.aartiRepository

import com.example.bjad.Model.AartiData
import com.example.bjad.Model.MusicData
import com.example.bjad.util.UiState

interface AartiRepository {

    suspend fun getAartiList(result:(UiState.Success<ArrayList<AartiData>>) -> Unit)

}