package com.example.bjad.ui.homeUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bjad.R
import com.example.bjad.databinding.FragmentMainViewBinding
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
        val data:String
        binding.textTitle.setText(viewModel.setUser().toString())
        return binding.root
    }


}