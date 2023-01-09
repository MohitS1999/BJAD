package com.example.bjad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.Model.MusicData
import com.example.bjad.databinding.MusicViewBinding

class MusicAdapter(
    private val context: Context,
    private val musicList:ArrayList<MusicData>
): RecyclerView.Adapter<MusicAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MusicViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val musicdata:MusicData = musicList.get(position)
        Glide.with(holder.itemView)
            .load(musicdata.imageUrl)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.songImage)
        holder.title.text = musicdata.songName
        holder.duration.text = musicdata.time.toString()


    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class MyHolder(binding:MusicViewBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.songName
        val duration = binding.time
        val songImage = binding.image
        val favBtn = binding.favBtn
    }

}