package com.example.myapplication.domain

import com.example.myapplication.data.db.Joke

interface JokeRepository {
    fun getAllJokes(): List<Joke>
    fun addJoke(joke: Joke)

}