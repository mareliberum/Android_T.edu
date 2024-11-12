package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityJokeDetailsBinding
import com.example.myapplication.databinding.ActivityMainBinding

class JokeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJokeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val question = intent.getStringExtra(JOKE_QUESTION)
        val answer = intent.getStringExtra(JOKE_ANSWER)
        val category = intent.getStringExtra(JOKE_CATEGORY)

        binding.question.text = question
        binding.answer.text = answer
        binding.category.text = category

    }
}