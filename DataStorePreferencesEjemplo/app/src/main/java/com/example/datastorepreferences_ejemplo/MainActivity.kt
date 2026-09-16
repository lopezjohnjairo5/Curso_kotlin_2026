package com.example.datastorepreferences_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.datastorepreferences_ejemplo.ui.theme.DataStorePreferencesEjemploTheme
import com.example.datastorepreferences_ejemplo.view.PantallaPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStorePreferencesEjemploTheme {
                /*
                * En este ejemplo almacenamos los valores
                * de nombre, modo oscuro y contador de manera
                * persistente de tal manera que al cerrar y re - abrir
                * la aplicacion sigan los nuevos valores presentes.
                * Esto se logra gracias a dataStore, el cual es el reemplazo de SharedPreferences.
                * dataStore:
                * - funciona con corrutinas
                * - tiene mejores validaciones para evitar el cierre inesperado o los errores de no responde la aplicacion
                * - funciona de manera asyncrona.
                * */
                PantallaPrincipal(
                    context =applicationContext
                )
            }
        }
    }
}

