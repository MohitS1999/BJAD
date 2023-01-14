package com.example.bjad.ui.audioSongs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.Model.MusicData
import com.example.bjad.R
import com.example.bjad.databinding.FragmentPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PlayerFragment"
@AndroidEntryPoint
class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var musicData: ArrayList<MusicData>
    private val viewModel by viewModels<PlayerViewModel> (  )
    private lateinit var url:String
    private var position:Int = 0
    private var isPlaying:Boolean = false
    private val clickOnShuffle:String= "shuffle"
    private val clickOnSongs:String= "songs"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // taking the data from previous fragment
        musicData = (arguments?.getSerializable("musicList") as? ArrayList<MusicData>)!!
        position = arguments?.getInt("position")!!

        // for shuffling the music
        if (arguments?.getString("onCallShuffle").equals(clickOnShuffle)){
            musicData.shuffle()
            initializeLayout()
        }
        if (arguments?.getString("onSongClicked").equals(clickOnSongs)){
            initializeLayout()
        }


        //for pause and play button
        binding.playPauseBtnPLY.setOnClickListener {
            if(isPlaying) pauseMusic()
            else playMusic()
        }
        // for previous
        binding.prevBtnPLY.setOnClickListener {
            prevNextSong(false)
        }
        // for next
        binding.nextBtnPLY.setOnClickListener {
            prevNextSong(true)
        }

    }

    private fun initializeLayout() {
        setContentLayout()
        viewModel.initSong(musicData.get(position).songUrl)
        isPlaying  = true
        binding.playPauseBtnPLY.setIconResource(R.drawable.pause_icon)
    }

    private fun setContentLayout() {
        Glide.with(this)
            .load(musicData.get(position).imageUrl)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(binding.imgPLY)
        binding.songNamePLY.text = musicData.get(position).songName
    }

    private fun playMusic(){
        binding.playPauseBtnPLY.setIconResource(R.drawable.pause_icon)
        isPlaying = true
        viewModel.playMusic()
    }

    private fun pauseMusic(){
        binding.playPauseBtnPLY.setIconResource(R.drawable.play_icon)
        isPlaying = false
        viewModel.pauseMusic()
    }

    private fun prevNextSong(increment:Boolean){
        if (increment){
            setSongPosition(increment)
            initializeLayout()
        }else{
            setSongPosition(increment)
            initializeLayout()
        }
    }

    private fun setSongPosition(increment:Boolean){
        // for next
        if (increment){
            if (musicData.size - 1 == position)
                position = 0
            else
                ++position
        }
        else    // for previous
        {
            if (position == 0)
                position = musicData.size - 1
            else
                --position
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }
}