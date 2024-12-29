package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.Joke
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.domain.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokeViewModel @Inject constructor(private val jokeDbRepository: JokeDbRepository) : ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private var _jokeList = MutableLiveData<List<Joke>>()
    val jokeList: LiveData<List<Joke>> = _jokeList


    fun fetchJokes() {
        if (_isLoading.value == true) return

        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO){
            //идем в БД с локальными
            val jokeListFromStaticDb = jokeDbRepository.getLoacalJokes()

            //если бд пустая - грузим из api
            if (jokeListFromStaticDb.isEmpty()){
                loadFromApi()
            }
            //ВЫгружаем из сетевого кэша
            val jokeListFromDb = jokeDbRepository.getJokesFromApiDatabase()

            //Общий список
            val jokeList = jokeListFromStaticDb + jokeListFromDb

            withContext(Dispatchers.Main){
                _isLoading.value = false
                _isError.value = false
                _jokeList.value = jokeList

            }
        }
    }

    suspend fun addJokeToLocalDatabase(joke: Joke){
        jokeDbRepository.addJokeToLocalDatabase(joke)
    }

    fun clearDB(){
        viewModelScope.launch(Dispatchers.IO){
            //Чистим сетевой кэш
            Log.d("test", "btn clear db pressed")
            jokeDbRepository.clearDb()
            val jokeListFromDb = jokeDbRepository.getJokesFromApiDatabase()
            Log.d("test", "jokeListFromDb after clearing - ${jokeListFromDb.size}")
            withContext(Dispatchers.Main){
                _jokeList.value = jokeListFromDb
            }

        }
    }

    fun fillStaticDb(){
        viewModelScope.launch(Dispatchers.IO) {
            val jokeListFromStaticDb = jokeDbRepository.getLoacalJokes()
            if (jokeListFromStaticDb.isEmpty()){
                jokeDbRepository.loadStaticJokes()
//                JokeDbRepositoryImpl(staticJokeDao).loadStaticJokes()
//                loadStaticJokes(staticJokeDao)
            }
        }
    }

    fun loadFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = jokeDbRepository.refreshJokes()
            if (!isSuccess) {
                withContext(Dispatchers.Main) {
                    _isError.value = true
                }
            }
        }
    }

    fun deleteJokeFromApiDatabase(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            Log.d("test", "opended view model,deleteJokeFromApiDatabase ")
            jokeDbRepository.deleteJokeFromApiDatabase(id)
        }

    }

    fun deleteJokeFromLocalDatabase(id : Int){
        viewModelScope.launch(Dispatchers.IO){
            jokeDbRepository.deleteJokeFromLoacalDatabaae(id)
        }
    }

}