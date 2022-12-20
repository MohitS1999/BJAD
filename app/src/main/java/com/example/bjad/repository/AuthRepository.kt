package com.example.bjad.repository

import com.example.bjad.Model.User
import com.example.bjad.util.UiState

interface AuthRepository {
    fun registerUser(email:String,password:String,user:User,result: (UiState<String>) -> Unit)
    fun loginUser(user: User,result: (UiState<String>) -> Unit)
    fun forgotPassword(user: User,result: (UiState<String>) -> Unit)
    fun updateUserInfo(user:User,result: (UiState<String>) -> Unit)
}