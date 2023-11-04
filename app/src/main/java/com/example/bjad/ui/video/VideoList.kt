package com.example.bjad.ui.video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bjad.Model.VideoData
import com.example.bjad.R
import com.example.bjad.adapter.VideoSongAdapter
import com.example.bjad.databinding.FragmentVideoListBinding
import com.example.bjad.ui.audio.MusicHome
import com.example.bjad.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_video_list.backBtn
import kotlinx.android.synthetic.main.music_list_view.view.*

private const val TAG = "VideoList"
@AndroidEntryPoint
class VideoList : Fragment() {
    private  lateinit var binding:FragmentVideoListBinding
    private val viewModel by viewModels<VideoViewModel>()
    private lateinit var videoList:ArrayList<VideoData>
    private lateinit var videoAdapter: VideoSongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentVideoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoList = ArrayList()
        observeSongs()

        binding.searchViewVideo.clearFocus()

        binding.searchViewVideo.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                try{
                    videoAdapter.filter.filter(newText.toString())
                }catch (e:NullPointerException){
                    Log.d(TAG, "onQueryTextChange: ${e.printStackTrace()}")
                }


                return false
            }

        })
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }






    private fun observeSongs() {
        viewModel.getSongs.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success  -> {
                    for (data in it.data) videoList.add(data)
                    Log.d(TAG, "observeSongs: $videoList")
                    updateRecyclerView()
                }
                is UiState.Loading -> {

                }
                is UiState.Failure -> {}
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView(){
        Log.d(TAG, "updateRecyclerView: ${videoList.size}")
        binding.videoRecyclerView.setHasFixedSize(true)
        binding.videoRecyclerView.setItemViewCacheSize(20)
        binding.videoRecyclerView.layoutManager = LinearLayoutManager(context)
        videoAdapter = VideoSongAdapter(requireActivity(),videoList,::onSongClicked)
        binding.videoRecyclerView.adapter = videoAdapter
        videoAdapter.notifyDataSetChanged()
    }
    private fun onSongClicked(list:ArrayList<VideoData>,position: Int){
        val bundle = Bundle()
        bundle.putInt("position",position)
        bundle.putParcelableArrayList("videoList",list)
        findNavController().navigate(R.id.action_videoList2_to_playerActivity,bundle)

    }

}