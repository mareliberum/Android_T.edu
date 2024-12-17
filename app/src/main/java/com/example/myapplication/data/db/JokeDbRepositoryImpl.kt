package com.example.myapplication.data.db

import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.domain.JokeDbRepository

class JokeDbRepositoryImpl(private val jokeDao: JokeDao) : JokeDbRepository {

    override suspend fun getAllJokes(): List<Joke> = jokeDao.getAllJokes()

    override suspend fun deleteExpired(expirationTime: Long) {

        jokeDao.deleteExpired(expirationTime)

    }

    override suspend fun addJoke(joke: Joke) {

        jokeDao.insert(joke)

    }

    override suspend fun clearDb() {
        jokeDao.clearDb()

    }


    override suspend fun refreshJokes(): Boolean {
        try {
            val jokesFromNet = RetrofitInstance.api.getJokes().jokes

            for (joke in jokesFromNet) {
                val category = joke.category
                val setup = joke.setup
                val delivery = joke.delivery
                val timeStamp = System.currentTimeMillis()
                val newJoke = Joke(id = 0, category, setup, delivery, isFromNet = true, timeStamp)
                addJoke(newJoke)
            }

            return true
        } catch (e: Exception) {

            return false
        }
    }



}