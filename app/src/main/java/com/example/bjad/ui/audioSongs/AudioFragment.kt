package com.example.bjad.ui.audioSongs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bjad.Model.MusicData
import com.example.bjad.R
import com.example.bjad.adapter.MusicAdapter
import com.example.bjad.databinding.FragmentAudioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioFragment : Fragment() {
    private lateinit var binding:FragmentAudioBinding
    private lateinit var musicAdapter:MusicAdapter
    private lateinit var musicDataList: ArrayList<MusicData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAudioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.favoriteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_audioFragment_to_favoriteFragment,Bundle().apply {  })
        }
        binding.playlistBtn.setOnClickListener {
            findNavController().navigate(R.id.action_audioFragment_to_playlistFragment,Bundle().apply {  })
        }
        binding.shuffleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_audioFragment_to_playerFragment,Bundle().apply {  })
        }

    }

    private fun updateRecyclerView(){

        musicDataList = ArrayList()
        binding.songsRecyclerView.setHasFixedSize(true)
        binding.songsRecyclerView.setItemViewCacheSize(20)
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(context)
        musicAdapter = MusicAdapter(requireActivity(),musicDataList)
        binding.songsRecyclerView.adapter = musicAdapter
        binding.totalSongs.text = "Total Songs : "+musicDataList.size

    }

}