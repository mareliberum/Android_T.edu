package com.example.myapplication.domain

interface JokeDbRepository {
    suspend fun refreshJokes() : Boolean
}