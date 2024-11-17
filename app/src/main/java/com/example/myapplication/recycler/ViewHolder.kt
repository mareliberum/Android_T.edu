package com.example.myapplication.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Joke
import com.example.myapplication.databinding.ItemViewBinding

class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke){

        binding.categoryTextView.text = joke.category
        binding.questionTextView.text = joke.question
        binding.answerTextView.text = joke.answer
    }



}