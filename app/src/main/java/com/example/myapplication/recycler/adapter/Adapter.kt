package com.example.myapplication.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.recycler.ViewHolder
import com.example.myapplication.recycler.util.DiffUtilCallback

class Adapter : RecyclerView.Adapter<ViewHolder>() {
    private var jokes = mutableListOf<Joke>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jokes[position])
    }


    fun setItems(list: List<Joke>) {
        val diffUtilCallback = DiffUtilCallback(jokes, list)
        val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
        jokes.clear()
        jokes.addAll(list)
        calculatedDiff.dispatchUpdatesTo(this)

    }

}