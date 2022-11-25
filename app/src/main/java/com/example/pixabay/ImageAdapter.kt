package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay.databinding.ItemImageBinding
import okhttp3.internal.notify

class ImageAdapter(val list: ArrayList<ImageModel>) : Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ItemImageBinding) : ViewHolder(binding.root){
        fun bind(imageModel: ImageModel){
            binding.imageView.load(imageModel.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addImage(image: ImageModel){
        list.add(image)
    }

    fun clearResult(){
        list.clear()
    }

}