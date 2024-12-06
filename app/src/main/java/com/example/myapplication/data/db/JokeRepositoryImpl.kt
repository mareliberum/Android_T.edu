package com.example.myapplication.data.db

import com.example.myapplication.domain.JokeRepository

object JokeRepositoryImpl : JokeRepository {

    private val jokes = mutableListOf(
        Joke(
            id = 0,
            "Christmas",
            "What does Santa suffer from if he gets stuck in a chimney?",
            "Claustrophobia!",
            false,
        ),

        Joke(0,"Math", "Why was the math book sad?", "Because it had too many problems.",false),
        Joke(0,"Animals", "Why don't some fish play piano?", "Because they can't tuna fish.", false),
        Joke(0, "Tech", "Why did the computer go to the doctor?", "Because it had a virus.", false),

        Joke( 0, "School", "Why was the student's report card wet?", "Because it was below sea level.", false),
        Joke(0,"Science", "Why can't you trust an atom?", "Because they make up everything.", false),
        Joke(0, "Food", "Why don't eggs tell jokes?", "Because they might crack up.", false),

        Joke(0, "Math", "Why was the math book sad?", "Because it had too many problems.", false),
        Joke(0, "Science", "Why can't you trust an atom?", "Because they make up everything.", false),
        Joke(0, "Food", "Why don't eggs tell jokes?", "Because they might crack up.", false)

    )


    override fun getAllJokes(): List<Joke> {
        return jokes.toList()
    }

    override fun addJoke(joke: Joke) {
        jokes.add(joke)
    }

}