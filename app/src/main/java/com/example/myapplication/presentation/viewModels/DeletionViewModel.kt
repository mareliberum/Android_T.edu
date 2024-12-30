package com.example.myapplication.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.di.modules.AppDatabaseDao
import com.example.myapplication.domain.JokeDbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeletionViewModel @Inject constructor(private val jokeDbRepository: JokeDbRepository): ViewModel() {

    @Inject
    @AppDatabaseDao
    lateinit var jokeDao: JokeDao

    //TODO : дополнить и исправить
    fun scheduleCleanUp(jokeDao: JokeDao) {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                deleteExpired(jokeDao)
                delay(60 * 60 * 1000) // Check for expired data every 1 hour
            }
        }
    }

    private fun deleteExpired(jokeDao: JokeDao) {
        val currentTime: Long = System.currentTimeMillis()
        val expirationTime: Long = 24 * 60 * 60 * 1000     //  24 hours in mills
        viewModelScope.launch(Dispatchers.IO) {
            jokeDbRepository.deleteExpired(currentTime - expirationTime)

        }

    }
}