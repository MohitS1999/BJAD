package com.example.bjad.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.Model.MusicData
import com.example.bjad.databinding.MusicListViewBinding
import java.util.Locale

private const val TAG = "VideoSongAdapter"
class MusicAdapter(
    private val context: Context,
    private val audioList:ArrayList<MusicData>,
    private val onSongClicked: (ArrayList<MusicData>,Int) -> Unit
) :RecyclerView.Adapter<MusicAdapter.MyHolder>(),Filterable{

    private var audioListFull:ArrayList<MusicData> = ArrayList(audioList)
    class MyHolder(binding:MusicListViewBinding):RecyclerView.ViewHolder(binding.root){
        val songName = binding.songMusicName
        val image =binding.imageMuiscView
        val singerName = binding.singerName
        val time = binding.timeMusic
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MusicListViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val audioData:MusicData = audioList.get(position)
        Glide.with(holder.itemView)
            .load(audioData.imageUrl)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.image)
        holder.singerName.text = audioData.singerName
        holder.time.text = audioData.time
        if (Locale.getDefault().language == "en"){
            Log.d(TAG, "onBindViewHolder: system language is english")
            holder.songName.text = audioData.songName
        }else if(Locale.getDefault().language == "hi"){
            if (audioData.songNameHi.isEmpty()){
                Log.d(TAG, "onBindViewHolder: hindi string is empty")
                holder.songName.text = audioData.songName
            }else {
                Log.d(TAG, "onBindViewHolder: ${audioData.songNameHi}")
                holder.songName.text = audioData.songNameHi
            }
        }else{
            Log.d(TAG, "onBindViewHolder: system language else")
            holder.songName.text = audioData.songName
        }


        holder.itemView.setOnClickListener {
            onSongClicked(audioList,position)
        }
    }

    override fun getItemCount(): Int {
        return audioList.size
    }

    override fun getFilter(): Filter {
        return filterUser()
    }
    private fun filterUser() = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val searchText:String = constraint.toString().toLowerCase()
            val temp:ArrayList<MusicData> = ArrayList()
            if (searchText.length == 0 || searchText.isEmpty()){
                temp.addAll(audioListFull)
            }else{
                for (document in audioListFull){
                    Log.d(TAG, "performFiltering: $document")
                    if (document.songName?.toLowerCase()?.contains(searchText) == true)
                        temp.add(document)
                }
            }
            Log.d(TAG, "performFiltering: $temp")
            val filterResults = FilterResults()
            filterResults.values = temp
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            audioList.clear()
            if (results != null){
                audioList.addAll(results.values as Collection<MusicData>)
            }
            notifyDataSetChanged()
        }

    }
}