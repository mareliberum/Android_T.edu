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


    fun scheduleCleanUp() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                deleteExpired()
                delay(60 * 60 * 1000) // Check for expired data every 1 hour
            }
        }
    }

    private fun deleteExpired() {
        val currentTime: Long = System.currentTimeMillis()
        val expirationTime: Long = 12 * 60 * 60 * 1000     //  12 hours in mills
        viewModelScope.launch(Dispatchers.IO) {
            jokeDbRepository.deleteExpired(currentTime - expirationTime)

        }

    }
}