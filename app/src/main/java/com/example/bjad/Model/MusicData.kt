package com.example.bjad.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class MusicData(
    val songName:String = "",
    val songUrl:String = "",
    val imageUrl:String ="",
    val time:Long = 0
)
