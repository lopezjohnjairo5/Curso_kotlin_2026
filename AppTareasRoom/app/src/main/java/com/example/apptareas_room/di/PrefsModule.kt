package com.example.apptareas_room.di

import android.content.Context
import com.example.apptareas_room.ui.theme.ThemePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefsModule {
    @Provides
    @Singleton
    fun provideThemePreferences(
        @ApplicationContext context : Context
    ): ThemePreferences = ThemePreferences(context)
    /*
    * Lo anterior crea una sola instancia del contexto
    * para que pueda ser usado en cualquier parte,
    * de no hacerlo así, seria necesario pasar el contexto cada
    * vez que se requiera cambiar el tema
    * */
}