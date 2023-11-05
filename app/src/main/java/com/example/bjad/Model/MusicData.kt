package com.example.bjad.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


private const val TAG = "MusicData"
data class MusicData(
    val songName:String = "",
    val songNameHi:String = "",
    val songUrl:String = "",
    val imageUrl:String ="",
    val singerName:String ="",
    val time:String ="",
    var isFavourite: Boolean = false
) : java.io.Serializable


