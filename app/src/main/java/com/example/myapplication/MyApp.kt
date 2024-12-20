package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent

class MyApp : Application() {
    lateinit var appComponent : AppComponent
    override fun onCreate() {

        //Инициализируем тут dagger component
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }
}