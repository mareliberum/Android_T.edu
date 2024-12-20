package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.data.db.JokeDao
import com.example.myapplication.data.db.StaticDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

//модуль нужен, чтобы рассказать Dagger, как создать DAO
@Module
class DatabaseModule {

    //Если где-то возникает потребность в AppDatabase, то dagger ищет среди своих provides метод, который ее предоставляет
    @Provides
    fun provideAppDatabase(context: Context): AppDataBase{
        return AppDataBase.initDatabase(context)
    }

    @Provides
    fun provideStaticDatabase(context: Context) : StaticDataBase{
        return StaticDataBase.initDatabase(context)
    }
    //Соответственно здесь, когда в аргументе понадобится AppDatabase, dagger обратится к provide выше
    @Provides
    @AppDatabaseDao
    fun provideJokeDao(dataBase: AppDataBase): JokeDao {
        return dataBase.JokeDao()
    }

    @Provides
    @StaticDatabaseDao
    fun provideStaticJokeDao(staticDataBase: StaticDataBase) : JokeDao{
        return staticDataBase.JokeDao()
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppDatabaseDao

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StaticDatabaseDao

