package com.example.myapplication

import JokeListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragments)
        if(savedInstanceState == null){
            openFragment()
        }
    }

    private fun openFragment(){
        val fragment = JokeListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()

    }

    fun onJokeClick(joke: Joke){
        val fragment = JokeDetailsFragment.newInstance(
            joke.question,
            joke.answer,
            joke.category
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Укажите ID контейнера для фрагментов
            .addToBackStack(null) // Добавляем в backstack для навигации
            .commit()
    }
}