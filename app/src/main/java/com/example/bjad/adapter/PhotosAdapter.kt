package com.example.bjad.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.R
import com.makeramen.roundedimageview.RoundedImageView

private const val TAG = "PhotosAdapter"
class PhotosAdapter(
    private val photosList: ArrayList<String>,
    private val onPhotoClicked: (ArrayList<String>, Int) -> Unit
) :
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
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: callind the method onPhotoClicked which is defined in PhotosFragment.kt")
            onPhotoClicked(photosList,position)
        }
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : RoundedImageView = itemView.findViewById(R.id.photos_view)
    }
}