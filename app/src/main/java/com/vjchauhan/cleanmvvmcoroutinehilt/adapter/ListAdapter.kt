package com.vjchauhan.cleanmvvmcoroutinehilt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ListRowBinding
import com.vjchauhan.cleanmvvmcoroutinehilt.model.ModelItem

class ListAdapter:RecyclerView.Adapter<ListAdapter.NewsViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<ModelItem>(){
        override fun areItemsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListRowBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding:ListRowBinding):
        RecyclerView.ViewHolder(binding.root){
           fun bind(model: ModelItem){
               Log.i("MYTAG","came here ${model.title}")
               binding.title .text = model.title
               binding.desc.text = model.body
               binding.userid.text = model.userId.toString()

               Glide.with(binding.imageView.context).
               load(model.url).
               into(binding.imageView)

               binding.root.setOnClickListener {
                  onItemClickListener?.let {
                        it(model)
                  }
               }
           }
        }

        private var onItemClickListener :((ModelItem)->Unit)?=null

        fun setOnItemClickListener(listener : (ModelItem)->Unit){
            onItemClickListener = listener
        }


}









