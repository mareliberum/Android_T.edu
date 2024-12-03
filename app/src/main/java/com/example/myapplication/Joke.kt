package com.example.myapplication

data class Joke(
    val id: String,
    val category: String,
    val question: String,
    val answer: String,
    val isFromNet : Boolean
)