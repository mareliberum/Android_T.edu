package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.recycler.adapter.Adapter
//import com.example.myapplication.recycler.adapter.JokeListAdapter
//import com.example.myapplication.recycler.util.JokeItemCallback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
        val jokeList = createJokeList()

        val adapter = Adapter()
        //val itemCallback = JokeItemCallback()
        //val adapter = JokeListAdapter(itemCallback)

        binding.recyclerView.adapter = adapter
        adapter.setItems(jokeList)
        //adapter.submitList(jokeList)

        val button = binding.changeButton
        val changedJokeList = listOf(
            Joke(
                1,
                "Christmas",
                "What does Santa suffer from if he gets stuck in a chimney?",
                "Claustrophobia!"
            ),
            Joke(2, "Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke(7, "Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )
        button.setOnClickListener {
            //adapter.submitList(changedJokeList)
            adapter.setItems(changedJokeList)
        }
    }

    private fun createJokeList(): List<Joke> {
        val jokeList = listOf(
            Joke(
                1,
                "Christmas",
                "What does Santa suffer from if he gets stuck in a chimney?",
                "Claustrophobia!"
            ),
            Joke(2, "Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke(3, "Animals", "Why don't some fish play piano?", "Because they can't tuna fish."),
            Joke(4, "Tech", "Why did the computer go to the doctor?", "Because it had a virus."),
            Joke(
                5,
                "School",
                "Why was the student's report card wet?",
                "Because it was below sea level."
            ),
            Joke(6, "Science", "Why can't you trust an atom?", "Because they make up everything."),
            Joke(7, "Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )
        return jokeList
    }

}