package com.example.bjad.ui.audioSongs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bjad.Model.MusicData
import com.example.bjad.repository.songsRepository.SongsRepository
import com.example.bjad.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "AudioViewModel"
@HiltViewModel
class AudioViewModel @Inject constructor(
    private val repository: SongsRepository
): ViewModel() {


    private val _getSongs = MutableLiveData<UiState<ArrayList<MusicData>>>()
    val getSongs:LiveData<UiState<ArrayList<MusicData>>>
        get() = _getSongs







    /*fun playSong(
        url:String
    ){
        _playSong.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.playSong(
                url = url
            ){
                Log.d(TAG, "playSong: $it")
                _playSong.value = it
            }
        }
    }*/

    fun getAllSongs() {
        _getSongs.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSongs {
                _getSongs.value = it
            }
        }
    }

}