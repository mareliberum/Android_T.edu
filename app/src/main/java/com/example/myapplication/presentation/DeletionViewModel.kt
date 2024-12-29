package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.data.db.JokeDbRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DeletionViewModel : ViewModel() {
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
            JokeDbRepositoryImpl(jokeDao).deleteExpired(currentTime - expirationTime)

        }

    }
}