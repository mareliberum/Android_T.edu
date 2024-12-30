package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.MyApp
import com.example.myapplication.R
import com.example.myapplication.data.db.Joke
import com.example.myapplication.domain.JokeDbRepository
import com.example.myapplication.presentation.viewModels.DeletionViewModel
import com.example.myapplication.presentation.viewModels.JokeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    //TODO : favourites
    //TODO : Customize nav bar

    @Inject
    lateinit var jokeDbRepository: JokeDbRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        val deletionViewModel : DeletionViewModel by viewModels { JokeViewModelFactory(jokeDbRepository) }

        deletionViewModel.scheduleCleanUp()

        setContentView(R.layout.activity_main_fragments)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottom_navigation) as NavHostFragment
        val navController = navHostFragment.navController

        AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.favourites
            )
        )

        navView.setupWithNavController(navController)

    }



    fun onJokeClick(joke: Joke) {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottom_navigation) as NavHostFragment
        val navController = navHostFragment.navController

        // Передаем данные через Bundle
        val bundle = Bundle().apply {
            putSerializable("joke", joke)
        }

        // Выполняем навигацию к JokeDetailsFragment
        navController.navigate(R.id.navigation_joke_details, bundle)
    }
}