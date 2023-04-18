package com.example.bjad.ui.homeUI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.Model.Results
import com.example.bjad.R
import com.example.bjad.databinding.FragmentMainViewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "MainViewFragment"

@AndroidEntryPoint
class MainViewFragment : Fragment() {

    lateinit var binding: FragmentMainViewBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var sunSetSunriseRes:Results


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // for go back to home page of phone
        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        Log.d(TAG, "onViewCreated: ")
        val cal = Calendar.getInstance()
        val month_date = SimpleDateFormat("EEE dd MMMM",Locale.ENGLISH)
        val month_name = month_date.format(cal.time)
        binding.dateHome.text = month_name.split(" ")[1] +"th "+ month_name.split(" ")[2].toUpperCase()
        binding.weekDayTV.text = month_name.split(" ")[0]
        //setUserName()
        //setSunsetSunriseTime()

        binding.photos.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_photosFragment,Bundle().apply {  })
        }
        binding.audioSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_audioActivity,Bundle().apply {  })
        }
        binding.videoSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_videoList2,Bundle().apply {  })
        }

        binding.aartiIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainViewFragment_to_aarti,Bundle().apply {  })
        }


    }

    /*private fun setUserName() {
        viewModel.setUser.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    Log.d(TAG, "observer: Loading")
                }
                is UiState.Failure -> {

                    Toast.makeText(context,state.error, Toast.LENGTH_SHORT).show()
                }
                is UiState.Success -> {
                    Log.d(TAG, "observer: Success")
                    binding.textTitle.setText("hello, "+state.data)
                }
            }

        }
    }*/

    // how to get the data using retrofit api
  /*  private fun setSunsetSunriseTime() {
        viewModel.getSunsetSunrisedata.observe(viewLifecycleOwner) {
            when (it)  {
                is UiState.Success -> {
                    binding.sunrise.setText(it.data.results.sunrise)
                    binding.sunset.setText(it.data.results.sunset)
                }
                else -> {}
            }

        }

    }*/


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")

    }



}


