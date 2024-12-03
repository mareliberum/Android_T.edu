package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JokeViewModel : ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _loaded = MutableLiveData<Boolean>()
    val loaded: LiveData<Boolean> = _loaded

    fun fetchJokes() {
        _loaded.value = false

        if (_isLoading.value == true) return

        _isLoading.value = true

        CoroutineScope(Dispatchers.Main).launch {
            JokeRepository.loadJokes()
            _isLoading.value = false
            _loaded.value = true
        }
    }

}



