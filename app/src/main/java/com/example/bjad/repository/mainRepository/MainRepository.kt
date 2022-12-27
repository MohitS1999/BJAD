package com.example.bjad.repository.mainRepository

interface MainRepository {
    fun calendar(latitude:Int,longitude:Int)
    fun setUserName():String
}