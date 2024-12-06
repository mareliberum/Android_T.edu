package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.data.db.Joke
import com.example.myapplication.data.db.JokeDao


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDataBase.initDatabase(this)
        setContentView(R.layout.activity_main_fragments)
        val jokeDao = AppDataBase.INSTANCE.JokeDao()
        if (savedInstanceState == null) {
            openFragment(jokeDao)
        }

        val deletionViewModel : DeletionViewModel by viewModels()

        deletionViewModel.scheduleCleanUp(jokeDao)

    }

    private fun openFragment(jokeDao: JokeDao) {

        val fragment = JokeListFragment(jokeDao)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun onJokeClick(joke: Joke) {
        val fragment = JokeDetailsFragment.newInstance(
            joke.setup,
            joke.delivery,
            joke.category
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Укажите ID контейнера для фрагментов
            .addToBackStack(null) // Добавляем в backstack для навигации
            .commit()
    }
}