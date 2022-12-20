package com.example.bjad.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bjad.Model.User
import com.example.bjad.repository.AuthRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel(){
    private val _register = MutableLiveData<UiState<String>>()
    val register:LiveData<UiState<String>>
        get() = _register

    fun register(
        email: String,
        password:String,
        user:User
    ){
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = user
        ){ _register.value = it}
    }
}