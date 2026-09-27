package com.example.apptareas_room.ui.theme

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

// creamos una instancia de dataStore asociada al contexto, en settings se guardan las preferencias
val Context.dataStore by preferencesDataStore(name = "settings")

private val KEY_THEME_MODE = intPreferencesKey("theme_mode") // clave unica almacenada en la key o llave, ej: 0 -> system, 1->light, 2->dark

class ThemePreferences (private val context: Context){

    // funcion que permite guardar un nuevo valor para el tema
    suspend fun setTheme(mode: ThemeMode){
        // abrimos el archivo de preferencias
        context.dataStore.edit { prefs ->
            prefs[KEY_THEME_MODE] = when (mode){
                ThemeMode.SYSTEM -> 0
                ThemeMode.LIGHT -> 1
                ThemeMode.DARK -> 2
            }
        }
    }

    // funcion para leer el nuevo modo o tema
    val themeModeFlow = context.dataStore.data.map { prefs ->
        when(prefs[KEY_THEME_MODE] ?: 0){
            1 -> ThemeMode.LIGHT
            2 -> ThemeMode.DARK
            else -> ThemeMode.SYSTEM
        }
    }


}