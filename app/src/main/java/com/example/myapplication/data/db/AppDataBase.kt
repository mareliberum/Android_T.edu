package com.example.myapplication.data.db

import androidx.room.Database
import com.example.myapplication.Joke

@Database(entities = [Joke :: class], version = 1)
abstract class AppDataBase {
    abstract fun JokeDao(): JokeDao
}