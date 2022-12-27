package com.example.bjad.repository.authRepository

import com.example.bjad.Model.User
import com.example.bjad.util.UiState

interface AuthRepository {
    fun registerUser(email:String,password:String,user:User,result: (UiState<String>) -> Unit)
    fun loginUser(email: String,password:String,result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String,result: (UiState<String>) -> Unit)
    fun updateUserInfo(user:User,result: (UiState<String>) -> Unit)
}