package com.example.bjad.ui.homeUI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bjad.Model.SunsetSetriseModel
import com.example.bjad.repository.mainRepository.MainRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel(){


    private val _getSunsetSunrisedata = MutableLiveData<SunsetSetriseModel>()
    val getSunsetSunrisedata:LiveData<SunsetSetriseModel>
    get() = _getSunsetSunrisedata

    private val _setUser = MutableLiveData<UiState<String>>()
    val setUser: LiveData<UiState<String>>
        get() = _setUser

    init {

        setUser()
        getSunsetSunrise()

    }

    private fun getSunsetSunrise()  = viewModelScope.launch(Dispatchers.IO) {
        repository.getSunsetSunrise().let {
            if (it.isSuccessful){
                _getSunsetSunrisedata.postValue(it.body())
            }else{
                Log.d(TAG, "getSunsetSunrise: ${it.code()}")
            }
        }
    }


    fun setUser(){
        _setUser.value = UiState.Loading
        repository.setUserName(){
            _setUser.value = it
        }
    }





}