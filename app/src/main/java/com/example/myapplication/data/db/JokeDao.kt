package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {


   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(joke : Joke)

   @Query("SELECT * FROM jokes ORDER BY  isFromNet ASC")
   suspend fun getAllJokes(): List<Joke>

   @Query("DELETE FROM jokes WHERE timeStamp < :expirationTime")
   suspend fun deleteExpired(expirationTime : Long)

   @Query("DELETE FROM jokes")
   suspend fun clearDb()

   @Query("DELETE FROM jokes WHERE id == :id")
   suspend fun delete(id : Int)

}