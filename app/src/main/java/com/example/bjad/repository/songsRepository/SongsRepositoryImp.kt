package com.example.bjad.repository.songsRepository

import android.media.AudioAttributes
import android.media.MediaPlayer
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
    private lateinit var mediaPlayer:MediaPlayer


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


    override fun initiableMediaPlayerObject() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    // for playing the song

    override suspend fun initSong(url: String) {
        Log.d(TAG, "initSong: ${mediaPlayer.isPlaying}")
        if (mediaPlayer.isPlaying){
            Log.d(TAG, "initSong: ----- ${mediaPlayer.isPlaying}")
            mediaPlayer.stop()
            mediaPlayer.reset()
        }

        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()
        }

    override fun play() {
        mediaPlayer.start()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun destroy() {
        Log.d(TAG, "destroy: ")
        mediaPlayer.release()
        
    }




}