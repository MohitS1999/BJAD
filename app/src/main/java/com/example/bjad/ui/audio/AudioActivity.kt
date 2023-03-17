package com.example.bjad.ui.audio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bjad.R

class AudioActivity : AppCompatActivity() {
    private lateinit var audioNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        val audioNavHost = supportFragmentManager
            .findFragmentById(R.id.audioFragmentContainerView) as NavHostFragment
        audioNavController =audioNavHost.findNavController()
    }
}