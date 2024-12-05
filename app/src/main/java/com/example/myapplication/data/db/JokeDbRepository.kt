package com.example.myapplication.data.db

import android.util.Log
import com.example.myapplication.data.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeDbRepository(private val jokeDao: JokeDao) {

    fun getAllJokes(): List<Joke> = jokeDao.getAllJokes()

    fun addJoke(joke: Joke){
        insert(joke)
    }
    private fun insert(joke: Joke) {
        CoroutineScope(Dispatchers.IO).launch {
            jokeDao.insert(joke)
        }
    }

    suspend fun refreshJokes() : Boolean {
        try {
            val jokesFromNet = RetrofitInstance.api.getJokes().jokes

            for (joke in jokesFromNet){
                val category = joke.category
                val setup = joke.setup
                val delivery = joke.delivery
                val timeStamp = System.currentTimeMillis()
                val newJoke = Joke(id = 0, category, setup, delivery,isFromNet = true, timeStamp)
                insert(newJoke)

            }

            return true
        }catch (e : Exception){

            Log.d("test","connection error")
            return false
        }

    }
}