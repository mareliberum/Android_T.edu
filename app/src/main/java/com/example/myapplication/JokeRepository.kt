package com.example.myapplication

object JokeRepository {
    private val jokes = mutableListOf(
        Joke(
            "1",
            "Christmas",
            "What does Santa suffer from if he gets stuck in a chimney?",
            "Claustrophobia!"
        ),

        Joke("2", "Math", "Why was the math book sad?", "Because it had too many problems."),
        Joke("3", "Animals", "Why don't some fish play piano?", "Because they can't tuna fish."),
        Joke("4", "Tech", "Why did the computer go to the doctor?", "Because it had a virus."),

        Joke( "5", "School", "Why was the student's report card wet?", "Because it was below sea level."),
        Joke("6", "Science", "Why can't you trust an atom?", "Because they make up everything."),
        Joke("7", "Food", "Why don't eggs tell jokes?", "Because they might crack up."),

        Joke("8", "Math", "Why was the math book sad?", "Because it had too many problems."),
        Joke("9", "Science", "Why can't you trust an atom?", "Because they make up everything."),
        Joke("10", "Food", "Why don't eggs tell jokes?", "Because they might crack up.")
    )

    fun addJoke(joke: Joke){
        jokes.add(joke)
    }

    fun getJokes(): List<Joke> {
        return jokes.toList()
    }

    fun newList(): List<Joke>{
        return listOf(
            Joke("8","Math", "Why was the math book sad?", "Because it had too many problems."),
            Joke("9","Science", "Why can't you trust an atom?", "Because they make up everything."),
            Joke("10","Food", "Why don't eggs tell jokes?", "Because they might crack up.")
        )
    }


}