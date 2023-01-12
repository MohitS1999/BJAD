package com.example.bjad.repository.songsRepository

import android.util.Log
import com.example.bjad.Model.MusicData
import com.example.bjad.util.UiState
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firebase.firestore.ktx.toObject
import kotlin.math.log

private const val TAG = "SongsRepositoryImp"
class SongsRepositoryImp(
    private val database:FirebaseFirestore
) : SongsRepository {
    private lateinit var songsList:ArrayList<MusicData>

    override suspend fun getSongs(result: (UiState<ArrayList<MusicData>>) -> Unit) {
        songsList = ArrayList()
        database.collection("AudioSongs")

            .addSnapshotListener{ snapshots ,e ->{

            }
                for (document in snapshots!!){
                    songsList.add(MusicData(document.data.get("song_name").toString(),document.data.get("song_url").toString(),document.data.get("img_url").toString()))

                }
                Log.d(TAG, "getSongs: $songsList")
                result.invoke(UiState.Success(songsList))
            }
    }


}