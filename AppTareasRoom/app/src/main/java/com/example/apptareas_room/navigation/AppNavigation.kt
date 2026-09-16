package com.example.apptareas_room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apptareas_room.ui.agregar_tarea.AgregarTareaScreen
import com.example.apptareas_room.ui.lista_tareas.ListaTareasScreen

@Composable
fun AppNavigation(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "pantallaListaTareas" // pantalla inicial
    ){
        // registrar rutas de las pantallas

        composable (
            route = "pantallaListaTareas"
        ){
            ListaTareasScreen(navController = navController)
        }

        composable (
            route = "pantallaAgregarTarea"
        ){
            AgregarTareaScreen(navController = navController)
        }

    }
}