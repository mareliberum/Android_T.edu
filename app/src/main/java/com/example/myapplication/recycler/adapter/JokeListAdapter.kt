package com.example.myapplication.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.recycler.ViewHolder
import com.example.myapplication.recycler.util.DiffUtilCallback
import com.example.myapplication.recycler.util.JokeItemCallback

class JokeListAdapter(itemCallback: JokeItemCallback) :
    ListAdapter<Joke, ViewHolder>(itemCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }




}