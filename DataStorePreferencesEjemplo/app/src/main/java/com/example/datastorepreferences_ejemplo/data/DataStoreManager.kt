package com.example.datastorepreferences_ejemplo.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// constante privada inmutable
private const val DATASTORE_NAME = "mi_datastore" // constante para acceder a data store, de nombre mi_datastore

// variable de contexto, propiedad de extension, Contexto nos da acceso a todoo lo que necesita nuestra aplicacion para funcionar, como por ejemplo los recursos o archivos del sistema
// con By delegamos a DATASTORE_NAME el funcionamiento
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager (private val context: Context) {

    // este es un bloque especial dentro de una clase, para declarar elementos estaticos.
    // sin necesidad de crear un objeto par usarlas
    companion object {
        // llaves
        val NOMBRE_KEY = stringPreferencesKey("nombre_usuario")
        val CONTADOR_KEY = intPreferencesKey("contador")
        val MODO_OSCURO_KEY = booleanPreferencesKey("modo_oscuro")
    }

    // funciones suspendidas ya que se ejecutan desde corrutinas, para recibir y guardar datos
    suspend fun guardarNombre(nombre: String) {
        // accedemos a dataStore. Con .edit obtenemos acceso para editar el contenido.
        context.dataStore.edit { prefs ->
            prefs[NOMBRE_KEY] = nombre
        }
    }

    suspend fun guardarContador(valor: Int) {
        context.dataStore.edit { prefs ->
            prefs[CONTADOR_KEY] = valor
        }
    }

    suspend fun guardarModoOscuro(estado: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[MODO_OSCURO_KEY] = estado
        }
    }


    // flows para escuchar que informacion ha sido almacenada en las llaves
    // para luego transmitirla a la interface

    val nombreFlow: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[NOMBRE_KEY] ?: ""
        }

    val contadorFlow: Flow<Int> = context.dataStore.data
        .map { prefs ->
            prefs[CONTADOR_KEY] ?: 0
        }

    val modoOscuroFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            prefs[MODO_OSCURO_KEY] ?: false
        }

}