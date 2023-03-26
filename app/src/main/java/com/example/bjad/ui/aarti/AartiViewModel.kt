package com.example.bjad.ui.aarti

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bjad.Model.AartiData
import com.example.bjad.repository.aartiRepository.AartiRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AartiViewModel"
@HiltViewModel
class AartiViewModel @Inject constructor(
    private val repository : AartiRepository
) : ViewModel(){

    private val _getAarti = MutableLiveData<UiState<ArrayList<AartiData>>>()
    val getAarti:LiveData<UiState<ArrayList<AartiData>>>
        get() = _getAarti

    init {
        getAllAarti()
    }

    private fun getAllAarti() {
        Log.d(TAG, "getAllAarti: ")
        _getAarti.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAartiList {
                Log.d(TAG, "getAllAarti: ${it.data}")
                _getAarti.value = it
            }
        }
    }

}