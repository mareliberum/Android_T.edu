package com.example.myapplication.domain

import com.example.myapplication.data.db.Joke

//TODO : уменьшить интерфейс и унаследовать от него 2 JokesRepository
interface JokeDbRepository {
    //методы для сетевого кэша
    suspend fun refreshJokes() : Boolean
    suspend fun getJokesFromApiDatabase(): List<Joke>
    suspend fun deleteExpired(expirationTime : Long)
    suspend fun clearDb()
    suspend fun addJokeToLocalDatabase(joke: Joke)
    suspend fun deleteJokeFromApiDatabase(id: Int)

    //методы для бд с локальными шуткми
    suspend fun deleteJokeFromLoacalDatabaae(id: Int)
    suspend fun loadStaticJokes()
    suspend fun getLoacalJokes() : List<Joke>

}