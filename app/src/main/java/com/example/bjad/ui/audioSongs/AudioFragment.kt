package com.example.bjad.ui.audioSongs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bjad.Model.MusicData
import com.example.bjad.R
import com.example.bjad.adapter.MusicAdapter
import com.example.bjad.databinding.FragmentAudioBinding
import com.example.bjad.ui.photos.PhotosViewModel
import com.example.bjad.util.UiState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "AudioFragment"

@AndroidEntryPoint
class AudioFragment : Fragment() {
    private lateinit var binding:FragmentAudioBinding
    private lateinit var musicAdapter:MusicAdapter
    private lateinit var musicDataList: ArrayList<MusicData>
    private val viewModel by viewModels<AudioViewModel> (  )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAudioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicDataList = ArrayList()
        observerSongs()
        viewModel.getAllSongs()
        binding.favoriteBtn.setOnClickListener {

            findNavController().navigate(R.id.action_audioFragment_to_favoriteFragment,Bundle().apply {  })
        }
        binding.playlistBtn.setOnClickListener {
            findNavController().navigate(R.id.action_audioFragment_to_playlistFragment,Bundle().apply {  })
        }
        binding.shuffleBtn.setOnClickListener {
            callShuffleFragment()

        }

    }

    private fun callShuffleFragment() {
        val bundle = Bundle()
        bundle.putString("onCallShuffle","shuffle")
        bundle.putSerializable("musicList",musicDataList as java.io.Serializable)
        bundle.putInt("position",0)
        findNavController().navigate(R.id.action_audioFragment_to_playerFragment,bundle)
    }

    private fun observerSongs() {
        viewModel.getSongs.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success -> {
                    for (data in it.data) musicDataList.add(data)
                    Log.d(TAG, "observerSongs: $musicDataList")
                    updateRecyclerView()
                }
                is UiState.Loading -> {

                }
                is UiState.Failure -> {}
            }
        }
    }

    private fun updateRecyclerView(){
        Log.d(TAG, "updateRecyclerView: ${musicDataList.size}")
        binding.songsRecyclerView.setHasFixedSize(true)
        binding.songsRecyclerView.setItemViewCacheSize(20)
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(context)
        musicAdapter = MusicAdapter(requireActivity(),musicDataList,::onSongClicked)
        binding.songsRecyclerView.adapter = musicAdapter
        binding.totalSongs.text = "Total Songs : "+musicDataList.size

    }
    private fun onSongClicked(list:ArrayList<MusicData>,position:Int){
        val bundle = Bundle()
        bundle.putString("onSongClicked","songs")
        bundle.putSerializable("musicList",list as java.io.Serializable)
        bundle.putInt("position",position)
        findNavController().navigate(R.id.action_audioFragment_to_playerFragment,bundle)

    }

}