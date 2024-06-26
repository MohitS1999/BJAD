package com.example.bjad.ui.video

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bjad.Model.VideoData
import com.example.bjad.repository.videoRepository.VideoRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VideoViewModel"
@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _getSongs = MutableLiveData<UiState<ArrayList<VideoData>>>()
    val getSongs: LiveData<UiState<ArrayList<VideoData>>>
        get() = _getSongs


    init {
        getAllVideos()
    }



    fun getAllVideos(){
        _getSongs.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getVideoSongs {
                Log.d(TAG, "getAllVideos: ${it.data}")
                _getSongs.value = it
            }
        }
    }

}