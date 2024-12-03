package com.example.myapplication.recycler

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
import com.example.myapplication.databinding.ItemViewBinding

class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke){
        binding.categoryTextView.text = joke.category
        binding.questionTextView.text = joke.question
        binding.answerTextView.text = joke.answer
        if (joke.isFromNet){
            binding.categoryTextView.setTextColor(Color.parseColor("#ffcc0000"))
        }
        else{
            binding.categoryTextView.setTextColor(Color.parseColor("#ff0099cc"))

        }
    }



}