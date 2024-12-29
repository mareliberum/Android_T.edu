package com.example.myapplication.data

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=twopart&amount=10")
    suspend fun getJokes() : JokeResponse

    @GET("/joke/Dark?amount=10")
    suspend fun getDarkJokes(): JokeResponse

}