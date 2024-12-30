package com.example.myapplication.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.JokeDbRepository
import javax.inject.Inject

class DeletionViewModelFactory @Inject constructor(private val jokeDbRepository: JokeDbRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DeletionViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DeletionViewModel(jokeDbRepository) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }
}