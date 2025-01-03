package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.presentation.AddJokeFragment
import com.example.myapplication.presentation.JokeDetailsFragment
import com.example.myapplication.presentation.JokeListFragment
import dagger.BindsInstance
import dagger.Component


//интерфейс, чтобы сказать dagger, куда, собственно передать зависимости
@Component(modules = [DatabaseModule::class])
interface AppComponent {
    fun inject(fragment: JokeListFragment)

    fun inject(fragment: JokeDetailsFragment)

    fun inject(fragment: AddJokeFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}