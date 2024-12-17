package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.data.db.JokeDbRepositoryImpl
import com.example.myapplication.data.db.JokeRepositoryImpl
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

        viewModelScope.launch(Dispatchers.IO){
            var jokeListFromDb = JokeDbRepositoryImpl(jokeDao).getAllJokes()

            if (jokeListFromDb.isEmpty()){
                loadStaticJokes(jokeDao)
            }

            val isSuccess = JokeDbRepositoryImpl(jokeDao).refreshJokes()

            if(!isSuccess){
                withContext(Dispatchers.Main){
                    _isError.value = true
                }
            }

            jokeListFromDb = JokeDbRepositoryImpl(jokeDao).getAllJokes()

            withContext(Dispatchers.Main){
                _isLoading.value = false
                _isError.value = false
                _jokeList.value = jokeListFromDb

            }
        }
    }


    suspend fun loadStaticJokes(jokeDao: JokeDao){
        val jokeList = JokeRepositoryImpl.getAllJokes()
        for (joke in jokeList){
            addJoke(joke, jokeDao)
        }
    }

    suspend fun addJoke(joke: Joke, jokeDao: JokeDao){
        JokeDbRepositoryImpl(jokeDao).addJoke(joke)
    }

    fun clearDB(jokeDao: JokeDao){
        viewModelScope.launch(Dispatchers.IO){
            JokeDbRepositoryImpl(jokeDao).clearDb()
            val jokeListFromDb = JokeDbRepositoryImpl(jokeDao).getAllJokes()

            withContext(Dispatchers.Main){
                _jokeList.value = jokeListFromDb
            }
        }
    }


}