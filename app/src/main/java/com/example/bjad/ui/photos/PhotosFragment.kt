package com.example.bjad.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bjad.R
import com.example.bjad.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PhotosFragment"
@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private val viewModel by viewModels<PhotosViewModel>()
    private lateinit var binding: FragmentPhotosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        viewModel.getphotos.observe(viewLifecycleOwner){

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}