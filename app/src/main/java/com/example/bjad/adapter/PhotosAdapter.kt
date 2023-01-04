package com.example.bjad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bjad.R
import com.makeramen.roundedimageview.RoundedImageView

class PhotosAdapter(private val photosList:ArrayList<String>) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.photos_item,parent,false)
        return PhotosViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val imageUrl:String = photosList[position]
        Glide.with(holder.itemView)
            .load(imageUrl)
            .fitCenter()
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : RoundedImageView = itemView.findViewById(R.id.photos_view)
    }
}