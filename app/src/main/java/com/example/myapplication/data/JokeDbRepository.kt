package com.example.myapplication.data

import com.example.myapplication.Joke
import com.example.myapplication.data.db.JokeDao
import kotlinx.coroutines.flow.Flow

class JokeDbRepository(private val jokeDao: JokeDao){
    fun getAllJokes(): Flow<List<Joke>> = jokeDao.getAllJokes()

    suspend fun refreshJokes(){
        val jokesFromNet = RetrofitInstance.api.getJokes().jokes

        jokeDao.insertAll(jokesFromNet)

    }
 }