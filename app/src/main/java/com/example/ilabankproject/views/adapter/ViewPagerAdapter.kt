package com.example.ilabankproject.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ilabankproject.databinding.ViewpagerSliderBinding
import com.example.ilabankproject.model.ImageDataDto

class ViewPagerAdapter(
    private val context: Context,
    private val labelList: MutableList<ImageDataDto>?
) : RecyclerView.Adapter<ViewPagerAdapter.SlideDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideDataHolder {
        val binding =
            ViewpagerSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SlideDataHolder(binding)
    }

    override fun onBindViewHolder(holder: SlideDataHolder, position: Int) {
        Glide.with(context)
            .load(labelList?.get(position)?.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return labelList?.size!!
    }


    inner class SlideDataHolder(
        private var binding: ViewpagerSliderBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView get() = binding.imageView
    }
}