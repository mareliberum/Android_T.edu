package com.example.myapplication

import JokeListFragment
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.JokeViewModel
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.data.db.Joke


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDataBase.initDatabase(this)
        setContentView(R.layout.activity_main_fragments)
        if (savedInstanceState == null) {
            openFragment()
        }

        val jokeViewModel: JokeViewModel by viewModels()
        jokeViewModel.scheduleCleanUp()

    }

    private fun openFragment() {
        val fragment = JokeListFragment()

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