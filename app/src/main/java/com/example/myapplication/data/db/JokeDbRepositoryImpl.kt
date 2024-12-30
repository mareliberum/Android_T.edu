package com.example.myapplication.data.db

import android.util.Log
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.domain.JokeDbRepository
import javax.inject.Inject

class JokeDbRepositoryImpl @Inject constructor(private val jokeDao: JokeDao, private val staticJokeDao: JokeDao) : JokeDbRepository {

    //методы для работы с сетевым кэшем

    override suspend fun refreshJokes(): Boolean {
        try {
            val jokesFromNet = RetrofitInstance.api.getJokes().jokes
            Log.d("test", "loading from api")
            for (joke in jokesFromNet) {
                val category = joke.category
                val setup = joke.setup
                val delivery = joke.delivery
                val timeStamp = System.currentTimeMillis()
                val newJoke =
                    Joke(id = 0, category, setup, delivery, isFromNet = true, timeStamp)
                jokeDao.insert(newJoke)
            }

            return true
        } catch (e: Exception) {

            return false
        }
    }

    override suspend fun getJokesFromApiDatabase(): List<Joke> = jokeDao.getAllJokes()

    override suspend fun deleteExpired(expirationTime: Long) {

        jokeDao.deleteExpired(expirationTime)

    }

    override suspend fun clearDb() {
        jokeDao.clearDb()
    }

    override suspend fun deleteJokeFromApiDatabase(id: Int) {
        jokeDao.delete(id)

    }

    //  Методы взаимодействия с БД с локальными шутками

    override suspend fun loadStaticJokes() {
        val jokeList = JokeRepositoryImpl.getAllJokes()
        for (joke in jokeList) {
            addJokeToLocalDatabase(joke)
        }
    }

    override suspend fun deleteJokeFromLocalDatabase(id: Int) {
        staticJokeDao.delete(id)
    }

    override suspend fun addJokeToLocalDatabase(joke: Joke) {
        staticJokeDao.insert(joke)
    }

    override suspend fun getLocalJokes(): List<Joke> = staticJokeDao.getAllJokes()




}