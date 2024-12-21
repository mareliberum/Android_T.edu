package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.JokeDbRepository
import javax.inject.Inject

class JokeViewModelFactory @Inject constructor(private val jokeDbRepository: JokeDbRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(JokeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return JokeViewModel(jokeDbRepository) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }
}