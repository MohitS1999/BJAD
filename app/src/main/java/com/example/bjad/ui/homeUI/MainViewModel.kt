package com.example.bjad.ui.homeUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bjad.repository.mainRepository.MainRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel(){

    private val _setUser = MutableLiveData<UiState<String>>()
    val setUser: LiveData<UiState<String>>
        get() = _setUser

    fun setUser(){
        _setUser.value = UiState.Loading
        repository.setUserName(){
            _setUser.value = it
        }
    }

}