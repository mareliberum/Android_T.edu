package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.db.JokeRepositoryImpl
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.data.db.JokeDbRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel : ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private var _jokeList = MutableLiveData<List<Joke>>()
    val jokeList: LiveData<List<Joke>> = _jokeList


    fun fetchJokes(jokeDao: JokeDao) {
        if (_isLoading.value == true) return

        _isLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = JokeDbRepositoryImpl(jokeDao).refreshJokes()

            if(!isSuccess){
                withContext(Dispatchers.Main){
                    _isError.value = true
                }
            }

            val jokeListFromDb = JokeDbRepositoryImpl(jokeDao).getAllJokes()
            if (jokeListFromDb.isEmpty()){
                loadStaticJokes(jokeDao)
            }
            withContext(Dispatchers.Main){
                _isLoading.value = false
                _isError.value = false
                _jokeList.value = jokeListFromDb
            }
        }
    }

    fun loadStaticJokes(jokeDao: JokeDao){
        val jokeList = JokeRepositoryImpl.getAllJokes()
        for (joke in jokeList){
            addJoke(joke, jokeDao)
        }
    }

    fun addJoke(joke: Joke, jokeDao: JokeDao){
        JokeDbRepositoryImpl(jokeDao).addJoke(joke)
    }


}
