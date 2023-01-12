package com.example.bjad.ui.photos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bjad.repository.photosRepository.PhotosRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

private const val TAG = "PhotosViewModel"
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
):ViewModel(){

    private val _getphotos = MutableLiveData<UiState<ArrayList<String>>>()
    val getphotos:LiveData<UiState<ArrayList<String>>>
        get() = _getphotos

    init {
        getAllPhotos()
    }

    private fun getAllPhotos() {
        _getphotos.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPhotos {
                Log.d(TAG, "getAllPhotos: "+"Getting the data from repository")
                _getphotos.value = it
            }
        }
    }
}