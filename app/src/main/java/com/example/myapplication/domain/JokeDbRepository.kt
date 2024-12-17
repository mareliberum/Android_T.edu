package com.example.myapplication.domain

import com.example.myapplication.data.db.Joke

interface JokeDbRepository {
    suspend fun refreshJokes() : Boolean
    suspend fun getAllJokes(): List<Joke>
    suspend fun deleteExpired(expirationTime : Long)
    suspend fun clearDb()
    suspend fun addJoke(joke: Joke)

}