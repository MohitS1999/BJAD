package com.example.bjad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bjad.R
import com.example.bjad.ui.photos.FullImageFragment
import kotlinx.android.synthetic.main.full_view_image.view.*
import java.util.*
import kotlin.collections.ArrayList

class FullImageViewPagerAdapter(val context: Context, val imageList:ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // on below line we are initializing
        // our layout inflater.
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView:View = mLayoutInflater.inflate(R.layout.full_view_image,container,false)
        // on below line we are initializing
        // our image view with the id.
        val imageView:ImageView = itemView.findViewById(R.id.fullImageViewId) as ImageView
        //val imageView: ImageView = itemView.findViewById<View>(R.id.fullImageViewId) as ImageView

        // on below line we are setting
        // image resource for image view.
        Glide.with(context)
            .load(imageList.get(position))
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(R.drawable.error_icon)
            .into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }
    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as RelativeLayout)
    }
}