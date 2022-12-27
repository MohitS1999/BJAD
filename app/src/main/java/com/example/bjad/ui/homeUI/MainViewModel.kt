package com.example.bjad.ui.homeUI

import androidx.lifecycle.ViewModel
import com.example.bjad.repository.mainRepository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel(){

    fun setUser(){
        repository.setUserName()
    }

}