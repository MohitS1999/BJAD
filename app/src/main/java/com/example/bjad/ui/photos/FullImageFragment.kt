package com.example.bjad.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.bjad.adapter.FullImageViewPagerAdapter
import com.example.bjad.databinding.FragmentFullImageBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "FullImageFragment"
@AndroidEntryPoint
class FullImageFragment : Fragment() {
    private lateinit var binding: FragmentFullImageBinding
    private lateinit var viewPagerAdapter: FullImageViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFullImageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
    }

    private fun setInitialData() {
        Log.d(TAG, "setInitialData: data is coming from PhotosFragment when we click on photos")
        val position = arguments?.getInt("position")
        val list = arguments?.getStringArrayList("imageList") as ArrayList<String>

        viewPagerAdapter = FullImageViewPagerAdapter(requireActivity(),list)
        binding.viewPagerId.adapter = viewPagerAdapter
        if (position != null) {
            binding.viewPagerId.setCurrentItem(position)
        }
    }


}