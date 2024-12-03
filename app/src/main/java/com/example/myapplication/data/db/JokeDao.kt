package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.Joke
import androidx.room.Query
import com.example.myapplication.data.JokeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
   @Insert
   suspend fun insertAll(jokes: List<JokeItem>)

   @Insert
   suspend fun insert(joke : Joke)

   @Query("SELECT * FROM jokes")
   fun getAllJokes(): Flow<List<Joke>>

}