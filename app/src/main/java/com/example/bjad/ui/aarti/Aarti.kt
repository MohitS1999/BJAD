package com.example.bjad.ui.aarti

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bjad.Model.AartiData
import com.example.bjad.R
import com.example.bjad.adapter.AartiAdapter
import com.example.bjad.databinding.FragmentAartiBinding
import com.example.bjad.ui.audio.MusicHome
import com.example.bjad.util.UiState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Aarti"
@AndroidEntryPoint
class Aarti : Fragment() {

    lateinit var aartiList:ArrayList<AartiData>
    private lateinit var aartiAdapter: AartiAdapter
    private val viewModel by viewModels<AartiViewModel>()
    private lateinit var binding: FragmentAartiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAartiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        aartiList = ArrayList()
        
        observeAarti()

        binding.searchViewAarti.clearFocus()
        binding.searchViewAarti.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                try {
                    aartiAdapter.filter.filter(newText.toString())
                } catch (e:Exception){
                    Log.d(TAG, "onQueryTextChange: ${e.printStackTrace()}")
                }
                return false
            }

        })
    }
    
    private fun observeAarti(){
        viewModel.getAarti.observe(viewLifecycleOwner){
            when(it){
                is UiState.Success -> {
                    for (data in it.data) {
                        aartiList.add(data)
                        Log.d(TAG, "observeAarti: $data")
                    }
                    updateRecyclerView()

                }
                is UiState.Failure -> {
                    Log.d(TAG, "observeAarti: Failure")
                }
                is UiState.Loading -> {
                    Log.d(TAG, "observeAarti: Loading")
                    //need to update
                }
            }
        }
    }

    private fun updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ")
        binding.aartiRV.setHasFixedSize(true)
        binding.aartiRV.setItemViewCacheSize(20)
        binding.aartiRV.layoutManager = GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
        aartiAdapter = AartiAdapter(requireContext(),aartiList,::onAartiClicked )
        binding.aartiRV.adapter = aartiAdapter
    }

    private fun onAartiClicked(arrayList: ArrayList<AartiData>, i: Int) {
        Log.d(TAG, "onAartiClicked: ")
        val bundle = Bundle()
        bundle.putString("url",arrayList[i].aartiPdf)
        findNavController().navigate(R.id.action_aarti_to_PDFViewer,bundle)
    }


}