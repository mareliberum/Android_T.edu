package com.example.myapplication.recycler.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.JOKE_ANSWER
import com.example.myapplication.JOKE_CATEGORY
import com.example.myapplication.JOKE_QUESTION
import com.example.myapplication.Joke
import com.example.myapplication.JokeDetailsActivity
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.recycler.ViewHolder
import com.example.myapplication.recycler.util.DiffUtilCallback

class Adapter() : RecyclerView.Adapter<ViewHolder>() {
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
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_QUESTION, jokes[position].question)
                putExtra(JOKE_ANSWER, jokes[position].answer)
                putExtra(JOKE_CATEGORY, jokes[position].category)
            }
            context.startActivity(intent)
        }

    }


    fun setItems(list: List<Joke>) {
        val diffUtilCallback = DiffUtilCallback(jokes, list)
        val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
        jokes.clear()
        jokes.addAll(list)
        calculatedDiff.dispatchUpdatesTo(this)

    }

}