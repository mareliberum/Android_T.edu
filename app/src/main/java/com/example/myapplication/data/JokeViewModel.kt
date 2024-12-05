package com.example.myapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.JokeRepository
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel : ViewModel() {
    private val jokeDao = AppDataBase.INSTANCE.JokeDao()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private var _jokeList = MutableLiveData<List<Joke>>()
    val jokeList: LiveData<List<Joke>> = _jokeList

    fun fetchJokes() {
//        _loaded.value = false

        if (_isLoading.value == true) return

        _isLoading.value = true


        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = JokeDbRepository(jokeDao).refreshJokes()

            if(!isSuccess){
                Log.d("connection", "error fetching from net")

                withContext(Dispatchers.Main){
                    _isError.value = true
                }
            }

            val jokeListFromDb = JokeDbRepository(jokeDao).getAllJokes()
            withContext(Dispatchers.Main){
                _isLoading.value = false
                _isError.value = false
                _jokeList.value = jokeListFromDb
            }

        }
    }

    private fun deleteExpired(){
        val currentTime : Long = System.currentTimeMillis()
        val expirationTime : Long = 24*60*60*1000     //  24 hours in mills
        jokeDao.deleteExpired(currentTime- expirationTime)
    }

    fun scheduleCleanUp(){
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive){
                deleteExpired()
                Log.d("test","deleteExpired")
                delay(60*60*1000) // Check for expired data every 1 hour
            }
        }
    }

    fun loadStaticJokes(){
        val jokeList = JokeRepository.getJokes()
        for (joke in jokeList){
            addJoke(joke)
        }
    }

    fun addJoke(joke: Joke){
        JokeRepository.getJokes()
        JokeDbRepository(jokeDao).addJoke(joke)
    }


}
