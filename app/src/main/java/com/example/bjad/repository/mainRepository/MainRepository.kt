package com.example.bjad.repository.mainRepository

import com.example.bjad.Model.SunsetSetriseModel
import com.example.bjad.util.UiState
import retrofit2.Response

interface MainRepository {
    fun calendar(latitude:Int,longitude:Int)
    fun setUserName(result: (UiState<String>) -> Unit)
    suspend fun getSunsetSunrise() : Response<SunsetSetriseModel>
}