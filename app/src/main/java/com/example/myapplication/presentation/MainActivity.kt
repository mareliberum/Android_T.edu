package com.example.myapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao


class MainActivity : AppCompatActivity() {

    private lateinit var jokeDao : JokeDao
    private lateinit var staticJokeDao : JokeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        AppDataBase.initDatabase(this)
//        StaticDataBase.initDatabase(this)

        setContentView(R.layout.activity_main_fragments)

//        jokeDao = AppDataBase.INSTANCE.JokeDao()
//        staticJokeDao = StaticDataBase.INSTANCE.JokeDao()

        if (savedInstanceState == null) {
            openFragment()
        }

        //TODO вернуть deletionViewModel и внедрить туда зависимость
//        val deletionViewModel : DeletionViewModel by viewModels()

//        deletionViewModel.scheduleCleanUp(jokeDao)
    }

    private fun openFragment() {

        val fragment = JokeListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun onJokeClick(joke: Joke) {

        val fragment = JokeDetailsFragment(joke, jokeDao, staticJokeDao)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Укажите ID контейнера для фрагментов
            .addToBackStack(null) // Добавляем в backstack для навигации
            .commit()
    }
}