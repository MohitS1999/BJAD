package com.example.bjad.ui.audioSongs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bjad.R
import com.example.bjad.databinding.FragmentPlaylistBinding


class PlaylistFragment : Fragment() {

    private lateinit var binding:FragmentPlaylistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        return binding.root
    }


}