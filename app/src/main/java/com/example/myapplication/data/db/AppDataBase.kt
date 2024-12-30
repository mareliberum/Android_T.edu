package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Joke :: class], version = 5)
abstract class AppDataBase : RoomDatabase() {
    abstract fun JokeDao(): JokeDao

    companion object{
        @Volatile
        lateinit var INSTANCE: AppDataBase

        fun initDatabase(context: Context): AppDataBase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            return instance
        }
    }


}