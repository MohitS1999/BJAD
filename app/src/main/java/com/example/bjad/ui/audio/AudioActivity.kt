package com.example.bjad.ui.audio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bjad.R
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "AudioActivity"
@AndroidEntryPoint
class AudioActivity : AppCompatActivity() {
    private lateinit var audioNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        val audioNavHost = supportFragmentManager
            .findFragmentById(R.id.audioFragmentContainerView) as NavHostFragment
        audioNavController = audioNavHost.findNavController()


    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: ")
    }
}