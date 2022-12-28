package com.example.bjad.ui.homeUI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bjad.R
import com.example.bjad.databinding.FragmentMainViewBinding
import com.example.bjad.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainViewFragment"

@AndroidEntryPoint
class MainViewFragment : Fragment() {

    lateinit var binding: FragmentMainViewBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ")
        observer()
        viewModel.setUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

    }

    fun observer() {
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

}