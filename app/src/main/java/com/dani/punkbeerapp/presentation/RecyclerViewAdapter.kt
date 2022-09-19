package com.dani.punkbeerapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dani.punkbeerapp.data.model.Beer
import com.dani.punkbeerapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Beer>(){
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}

class MyViewHolder (
    private val binding: ListItemBinding,
    listener : RecyclerViewAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(beer : Beer) {
        binding.name.text = beer.name
        binding.tagline.text = beer.tagline
        val imageUrl = beer.imageUrl

        Picasso.with(binding.imageView.context).load(imageUrl).into(binding.imageView)
    }
    init {
        binding.root.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
}