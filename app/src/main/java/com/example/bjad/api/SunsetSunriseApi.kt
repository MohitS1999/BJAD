package com.example.bjad.api

import com.example.bjad.Model.SunsetSetriseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SunsetSunriseApi {

    @GET("json?lat=28.535517&lng=77.391029&date=today")
    suspend fun getTime(): Response<SunsetSetriseModel>

}