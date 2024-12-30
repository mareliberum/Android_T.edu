package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.presentation.MainActivity
import com.example.myapplication.presentation.fragments.AddJokeFragment
import com.example.myapplication.presentation.fragments.JokeDetailsFragment
import com.example.myapplication.presentation.fragments.JokeListFragment
import dagger.BindsInstance
import dagger.Component


//интерфейс, чтобы сказать dagger, куда, собственно передать зависимости
@Component(modules = [DatabaseModule::class])
interface AppComponent {
    fun inject(fragment: JokeListFragment)

    fun inject(fragment: JokeDetailsFragment)

    fun inject(fragment: AddJokeFragment)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }


}