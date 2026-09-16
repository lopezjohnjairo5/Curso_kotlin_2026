package com.example.apptareas_room

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
import androidx.navigation.compose.rememberNavController
import com.example.apptareas_room.navigation.AppNavigation
import com.example.apptareas_room.ui.agregar_tarea.AgregarTareaScreen
import com.example.apptareas_room.ui.lista_tareas.ListaTareasScreen
import com.example.apptareas_room.ui.theme.AppTareasRoomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // indica que se requiere inyeccion de dependencias
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTareasRoomTheme {
                /*
                * NOTAS:
                * - HILT : biblioteca oficial de inyección de dependencias para Android creada por Google y construida sobre Dagger.
                * - ROOM : biblioteca oficial de Android Jetpack que facilita el manejo de bases de datos locales en aplicaciones desarrolladas con Kotlin.
                * - las entidades son las tablas, es decir las estructuras de las tablas de la base de datos
                * - DAO es la interface de acceso a datos o Data Access Objetc, es aqui donde se crea el CRUD
                *
                *
                * */

                // creamos una instancia del controlador de navegacion
                val navController = rememberNavController() // conservamos una unica instancia gracias al remember
                AppNavigation(navController = navController) // contiene la navegacion y le pasamos el navcontroller
            }
        }
    }
}
