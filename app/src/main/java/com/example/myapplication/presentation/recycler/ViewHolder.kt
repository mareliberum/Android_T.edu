package com.example.myapplication.presentation.recycler

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.db.Joke
import com.example.myapplication.databinding.ItemViewBinding

class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke){
        binding.categoryTextView.text = joke.category
        binding.questionTextView.text = joke.setup
        binding.answerTextView.text = joke.delivery
        if (joke.isFromNet){
            binding.categoryTextView.setTextColor(Color.parseColor("#586249"))
        }
        else{
            binding.categoryTextView.setTextColor(Color.parseColor("#584663"))
        }
    }

}