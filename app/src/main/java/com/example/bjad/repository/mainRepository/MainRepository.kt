package com.example.bjad.repository.mainRepository

import com.example.bjad.util.UiState

interface MainRepository {
    fun calendar(latitude:Int,longitude:Int)
    fun setUserName(result: (UiState<String>) -> Unit)
}