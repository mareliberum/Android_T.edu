package com.example.myapplication.data

import retrofit2.http.GET


interface ApiService {
    @GET("/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=twopart&amount=10")
    suspend fun getJokes() : JokeResponse
}