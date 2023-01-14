package com.example.bjad.ui.audioSongs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bjad.repository.songsRepository.SongsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PlayerViewModel"
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: SongsRepository
):ViewModel() {

    init {

        initialzeMediaPlayerObject()

    }

    private fun initialzeMediaPlayerObject() {
        repository.initiableMediaPlayerObject()
    }

    fun initSong(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.initSong(url = url)
        }
    }

    fun playMusic() {
        repository.play()
    }
    fun pauseMusic(){
        repository.pause()
    }
    fun destroy(){
        repository.destroy()
    }
}