package com.example.myapplication.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.Joke

class JokeItemCallback : DiffUtil.ItemCallback<Joke>(){
    override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem == newItem
    }
}