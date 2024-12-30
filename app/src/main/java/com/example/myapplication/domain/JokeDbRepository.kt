package com.example.myapplication.domain

import com.example.myapplication.data.db.Joke

interface JokeDbRepository {

    suspend fun refreshJokes() : Boolean
    suspend fun deleteExpired(expirationTime : Long)
    suspend fun clearDb()
    suspend fun addJokeToLocalDatabase(joke: Joke)
    suspend fun deleteJokeFromApiDatabase(id: Int)
    suspend fun getJokesFromApiDatabase(): List<Joke>

    //методы для бд с локальными шуткми
    suspend fun deleteJokeFromLocalDatabase(id: Int)
    suspend fun loadStaticJokes()
    suspend fun getLocalJokes() : List<Joke>


}