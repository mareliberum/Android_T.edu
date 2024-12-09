package com.example.myapplication.data.db

import android.util.Log
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.domain.JokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeDbRepositoryImpl(private val jokeDao: JokeDao) : JokeRepository, JokeDbRepository{

    override fun getAllJokes(): List<Joke> = jokeDao.getAllJokes()

    override fun addJoke(joke: Joke) {
        CoroutineScope(Dispatchers.IO).launch {
            jokeDao.insert(joke)
        }
    }

    override suspend fun refreshJokes() : Boolean {
        try {
            val jokesFromNet = RetrofitInstance.api.getJokes().jokes

            for (joke in jokesFromNet){
                val category = joke.category
                val setup = joke.setup
                val delivery = joke.delivery
                val timeStamp = System.currentTimeMillis()
                val newJoke = Joke(id = 0, category, setup, delivery,isFromNet = true, timeStamp)
                addJoke(newJoke)
            }

            return true
        }catch (e : Exception){

            Log.d("test","connection error")
            return false
        }

    }
}