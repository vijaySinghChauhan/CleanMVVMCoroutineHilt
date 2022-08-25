package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.vjchauhan.cleanmvvmcoroutinehilt.databinding.ListRowBinding
import com.vjchauhan.cleanmvvmcoroutinehilt.data.model.ModelItem

class SampleListAdapter:RecyclerView.Adapter<SampleListAdapter.ViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<ModelItem>(){
        override fun areItemsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
            return oldItem.title  == newItem.title
        }

        override fun areContentsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListRowBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(
        val binding:ListRowBinding):
        RecyclerView.ViewHolder(binding.root){
           fun bind(model: ModelItem){
               Log.i("MYTAG","came here ${model.title}")
               binding.title .text = model.title
               binding.desc.text = model.userId.toString()
//               binding.userid.text = model.userId.toString()

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