package com.example.apptareas_room.di

import android.app.Application
import androidx.room.Room
import com.example.apptareas_room.data.local.dao.TareaDao
import com.example.apptareas_room.data.local.database.TareaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //indica que esta clase es un modulo de Hilt
@InstallIn(SingletonComponent::class) // esta le dice a hilt en donde se instalara el modulo en este caso en SingletonComponent

object AppModule {
    @Provides // proveemos la instancia unica de la base de datos
    @Singleton // indica que solo se creará una sola instancia durante la vida de la app
    fun provideTareaDatabase(context : Application) : TareaDatabase{
        return Room.databaseBuilder(
            context,
            TareaDatabase::class.java,
            "tarea_database"
        ).build() // construimos la instancia de la base de datos
    }

    @Provides
    @Singleton
    fun provideTareaDao(db: TareaDatabase) : TareaDao{
        return db.tareaDao() // retorna todoo el objeto dao ya construido
    }
}



