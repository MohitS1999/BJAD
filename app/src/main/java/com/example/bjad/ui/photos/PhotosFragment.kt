package com.example.bjad.ui.photos

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bjad.R
import com.example.bjad.adapter.PhotosAdapter
import com.example.bjad.databinding.FragmentPhotosBinding
import com.example.bjad.util.UiState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PhotosFragment"
@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private val viewModel by viewModels<PhotosViewModel>()
    private lateinit var binding: FragmentPhotosBinding
    private lateinit var photosRecyclerView: RecyclerView
    private lateinit var photosList:ArrayList<String>
    private lateinit var photoAdapter: PhotosAdapter



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

        photosList = ArrayList()
        observePhotos()

    }

    private fun observePhotos() {
        viewModel.getphotos.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success -> {

                    for (data in it.data) photosList.add(data)
                    photosRecyclerView = binding.photosRecyclerView
                    photosRecyclerView.setHasFixedSize(true)
                    photoAdapter = PhotosAdapter(photosList)
                    // on below line we are setting layout manager for our recycler view
                    val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    photosRecyclerView.layoutManager = staggeredGridLayoutManager
                    photosRecyclerView.adapter = photoAdapter
                    photoAdapter.notifyDataSetChanged()
                    //Log.d(TAG, "observePhotos: $photosList")
                }
                is UiState.Failure -> {
                    //TODO
                }
                is UiState.Loading -> {
                    // TODO
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}