package com.example.bjad.repository.musicRepository

import android.util.Log
import com.example.bjad.Model.MusicData
import com.example.bjad.util.UiState
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "MusicRepositoryImp"
class MusicRepositoryImp(
    private val database: FirebaseFirestore
) : MusicRepository {
    private lateinit var musicList:ArrayList<MusicData>

    override suspend fun getMusicSongs(result: (UiState.Success<ArrayList<MusicData>>) -> Unit) {

        musicList = ArrayList()

        database.collection("AudioSongs")

            .addSnapshotListener{ snapshots ,e ->{

            }
                for (document in snapshots!!){
                    musicList.add(MusicData(
                        document.data.get("song_name").toString(),
                        document.data.get("song_url").toString(),
                        document.data.get("img_url").toString(),
                        document.data.get("artist").toString()))
                }
                Log.d(TAG, "getSongs: $musicList")
                result.invoke(UiState.Success(musicList))
            }

    }
}