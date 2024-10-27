package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = GridLayoutManager(this,1)
        val jokeList = createJokeList()

        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
        adapter.setItems(jokeList)

        adapter.addItems(Joke("Test", "Lorem Ipsum", "Dolor sit amet"))
    }

    private fun createJokeList() : List<Joke>{
        val jokeList = listOf(
            Joke("Christmas", "What does Santa suffer from if he gets stuck in a chimney?", "Claustrophobia!"),
            Joke("Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke("Animals", "Why don't some fish play piano?", "Because they can't tuna fish."),
            Joke("Tech", "Why did the computer go to the doctor?", "Because it had a virus."),
            Joke("School", "Why was the student's report card wet?", "Because it was below sea level."),
            Joke("Science", "Why can't you trust an atom?", "Because they make up everything."),
            Joke("Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )
        return jokeList
    }

}