package com.example.myapplication.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.recycler.ViewHolder
import com.example.myapplication.recycler.util.DiffUtilCallback

class Adapter : RecyclerView.Adapter<ViewHolder>() {
    private var jokes = mutableListOf<Joke>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jokes[position])
        holder.itemView.setOnClickListener{
            (holder.itemView.context as? MainActivity)?.onJokeClick(jokes[position])
        }


    }


    companion object {
        fun setItems(adapter: Adapter, list: List<Joke>) {
            val diffUtilCallback = DiffUtilCallback(adapter.jokes, list)
            val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
            adapter.jokes.clear()
            adapter.jokes.addAll(list)
            calculatedDiff.dispatchUpdatesTo(adapter)

        }
    }



}