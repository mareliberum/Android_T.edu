package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.presentation.JokeListFragment
import dagger.BindsInstance
import dagger.Component


//интерфейс, чтобы сказать dagger, куда, собственно передать зависимости
@Component(modules = [DatabaseModule::class])
interface AppComponent {
    fun inject(fragment: JokeListFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}