package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MyApp
import com.example.myapplication.R
import com.example.myapplication.data.db.Joke
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.presentation.fragments.JokeDetailsFragment
import com.example.myapplication.presentation.fragments.JokeListFragment
import com.example.myapplication.presentation.viewModels.DeletionViewModel
import com.example.myapplication.presentation.viewModels.JokeViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var jokeDbRepository: JokeDbRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        val deletionViewModel : DeletionViewModel by viewModels { JokeViewModelFactory(jokeDbRepository) }

        deletionViewModel.scheduleCleanUp()

        setContentView(R.layout.activity_main_fragments)
        if (savedInstanceState == null) {
            openFragment()
        }


    }

    private fun openFragment() {

        val fragment = JokeListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun onJokeClick(joke: Joke) {

        val fragment = JokeDetailsFragment(joke)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Укажите ID контейнера для фрагментов
            .addToBackStack(null) // Добавляем в backstack для навигации
            .commit()
    }
}