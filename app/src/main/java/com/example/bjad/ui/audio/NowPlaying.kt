package com.example.bjad.ui.audio

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bjad.R
import com.example.bjad.databinding.FragmentNowPlayingBinding
import com.example.bjad.ui.audio.MusicPlayer.Companion.musicList
import com.example.bjad.util.getBitmapFromUrl
import com.example.bjad.util.setSongPosition

private const val TAG = "NowPlaying"
class NowPlaying : Fragment() {


    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var binding:FragmentNowPlayingBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //how to invisible the layout
        val view = inflater.inflate(R.layout.fragment_now_playing, container, false)
        binding = FragmentNowPlayingBinding.bind(view)

        binding.songMusicNamePA.isSelected = true



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playPauseBtnNP.setOnClickListener{
            if (MusicPlayer.isPlaying) pauseMusic() else playMusic()
        }

        binding.nextBtnNP.setOnClickListener {
            setSongPosition(true)
            PlayerViewModel.musicService!!.createMediaPlayer(musicList[MusicPlayer.position].songUrl)
            val img = getBitmapFromUrl(musicList[MusicPlayer.position].imageUrl,requireContext())
            binding.songImpNP.setImageBitmap(img)
            binding.songMusicNamePA.text = musicList[MusicPlayer.position].songName
            PlayerViewModel.musicService!!.showNotification(R.drawable.pause_music_icon)
            binding.songMusicNamePA.isSelected = true
            PlayerViewModel.musicService?.mediaPlayer?.setOnPreparedListener {
                PlayerViewModel.musicService!!.mediaPlayer!!.start()
            }
        }
        binding.root.setOnClickListener{
            Log.d(TAG, "onViewCreated: click on root")
            val bundle = Bundle()
            bundle.putString("onNowPlayedClicked","nowplaying")
            //bundle.putInt("pos",MusicPlayer.position)
            //bundle.putSerializable("musicList", musicList as java.io.Serializable)
            findNavController().navigate(R.id.action_musicHome_to_musicPlayer,bundle)
        }
    }


    override fun onResume() {
        super.onResume()
        if (PlayerViewModel.musicService != null){
            binding.songMusicNamePA.isSelected = true
            MusicHome.binding.nowPlaying.visibility = View.VISIBLE
            val img = getBitmapFromUrl(musicList[MusicPlayer.position].imageUrl,requireContext())
            binding.songImpNP.setImageBitmap(img)
            binding.songMusicNamePA.text = musicList[MusicPlayer.position].songName
            if (MusicPlayer.isPlaying) binding.playPauseBtnNP.setIconResource(R.drawable.pause_pa_icon)
            else binding.playPauseBtnNP.setIconResource(R.drawable.play_pa_icon)
        }
    }


    private fun playMusic(){
        PlayerViewModel.musicService!!.mediaPlayer!!.start()
        binding.playPauseBtnNP.setIconResource(R.drawable.pause_pa_icon)
        PlayerViewModel.musicService!!.showNotification(R.drawable.pause_music_icon)
        MusicPlayer.binding.nextMusicBtn.setImageResource(R.drawable.pause_music_icon)
        MusicPlayer.isPlaying = true
    }

    private fun pauseMusic(){
        Log.d(TAG, "pauseMusic: ")
        PlayerViewModel.musicService!!.mediaPlayer!!.pause()
        binding.playPauseBtnNP.setIconResource(R.drawable.play_pa_icon)
        PlayerViewModel.musicService!!.showNotification(R.drawable.play_music_icon)
        MusicPlayer.binding.nextMusicBtn.setImageResource(R.drawable.play_music_icon)
        MusicPlayer.isPlaying = false
    }


}