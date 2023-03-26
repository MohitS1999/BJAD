package com.example.bjad.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.Model.AartiData
import com.example.bjad.R
import com.example.bjad.databinding.AartiListViewBinding

private const val TAG = "AartiAdapter"
class AartiAdapter(
    private val context: Context,
    private val aartiList:ArrayList<AartiData>,
    private val onAartiClicked:(ArrayList<AartiData>,Int) -> Unit
) : RecyclerView.Adapter<AartiAdapter.AartiViewHolder>(),Filterable {

    private var aartiListFull:ArrayList<AartiData> = ArrayList(aartiList)

    class AartiViewHolder(binding:AartiListViewBinding): RecyclerView.ViewHolder(binding.root){

        val aartiName = binding.aartiTV
        val aartiImg = binding.aartiImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AartiViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return AartiViewHolder(AartiListViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: AartiViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ")
        val aartiData = aartiList[position]
        Glide.with(holder.itemView)
            .load(aartiData.imageUrl)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.aartiImg)
        holder.aartiName.text = aartiData.aartiName
        holder.itemView.setOnClickListener {
            onAartiClicked(aartiList,position)
        }
    }

    override fun getItemCount(): Int {
        return aartiList.size
    }

    override fun getFilter(): Filter {
        Log.d(TAG, "getFilter: ")
        return filterUser()
    }

    private fun filterUser() = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val searchText:String = constraint.toString().toLowerCase()
            val temp:ArrayList<AartiData> = ArrayList()
            if (searchText.length == 0 || searchText.isEmpty()){
                temp.addAll(aartiListFull)
            }else{
                for (document in aartiListFull){
                    Log.d(TAG, "performFiltering: $document")
                    if (document.aartiName?.toLowerCase()?.contains(searchText) == true){
                        temp.add(document)
                    }
                }
            }
            Log.d(TAG, "performFiltering: $temp")
            val filterResults = FilterResults()
            filterResults.values = temp
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            aartiList.clear()
            if (results != null){
                aartiList.addAll(results.values as Collection<AartiData>)
            }
            notifyDataSetChanged()
        }

    }


}