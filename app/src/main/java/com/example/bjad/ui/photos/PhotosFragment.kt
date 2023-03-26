package com.example.bjad.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                    Log.d(TAG, "observePhotos: Success")
                    for (data in it.data) photosList.add(data)
                    updateRecyclerView()
                }
                is UiState.Failure -> {
                    Log.d(TAG, "observePhotos: Failure")
                    //TODO
                }
                is UiState.Loading -> {
                    Log.d(TAG, "observePhotos: Loading")
                    // TODO
                }
            }
        }
    }

    private fun updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ")
        photosRecyclerView = binding.photosRecyclerView
        // for
        photosRecyclerView.setHasFixedSize(true)
        photoAdapter = PhotosAdapter(photosList,::onPhotoClicked)
        // on below line we are setting layout manager for our recycler view
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        photosRecyclerView.layoutManager = staggeredGridLayoutManager
        photosRecyclerView.adapter = photoAdapter
        photoAdapter.notifyDataSetChanged()

    }

    private fun onPhotoClicked(list: ArrayList<String>,position: Int){
        Log.d(TAG, "onPhotoClicked: ")
        val bundle = Bundle()
        bundle.putStringArrayList("imageList",list)
        bundle.putInt("position",position)
        findNavController().navigate(R.id.action_photosFragment_to_fullImageFragment,bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}