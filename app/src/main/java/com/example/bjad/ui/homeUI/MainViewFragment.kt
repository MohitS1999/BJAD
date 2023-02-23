package com.example.bjad.ui.homeUI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.Model.Results
import com.example.bjad.R
import com.example.bjad.databinding.FragmentMainViewBinding
import com.example.bjad.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_view.view.*

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
        setUserName()
        setSunsetSunriseTime()

        binding.photos.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_photosFragment,Bundle().apply {  })
        }
        binding.audioSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_audioFragment,Bundle().apply {  })
        }
        binding.videoSongs.setOnClickListener(){
            findNavController().navigate(R.id.action_mainViewFragment_to_videoList2,Bundle().apply {  })
        }
    }

    private fun setUserName() {
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
    }

    // how to get the data using retrofit api
    private fun setSunsetSunriseTime() {
        viewModel.getSunsetSunrisedata.observe(viewLifecycleOwner) {
            when (it)  {
                is UiState.Success -> {
                    binding.sunrise.setText(it.data.results.sunrise)
                    binding.sunset.setText(it.data.results.sunset)
                }
                else -> {}
            }

        }

    }



}


