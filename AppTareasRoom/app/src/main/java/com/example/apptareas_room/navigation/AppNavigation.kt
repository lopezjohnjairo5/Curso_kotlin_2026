package com.example.apptareas_room.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.apptareas_room.ui.agregar_tarea.AgregarTareaScreen
import com.example.apptareas_room.ui.ajustes.AjustesScreen
import com.example.apptareas_room.ui.detalle_tarea.DetalleTareaScreen
import com.example.apptareas_room.ui.editar_tarea.EditarTareaScreen
import com.example.apptareas_room.ui.lista_tareas.ListaTareasScreen
import com.example.apptareas_room.ui.viewmodel.TareaViewModel

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
            route = "pantallaAjustes"
        ){
            AjustesScreen(navController = navController)
        }
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

        composable (
            route = "editarTareaScreen/{tareaId}",
            arguments = listOf(navArgument("tareaId"){type = NavType.IntType}) // indica que el parametro que se pasa será un entero y debe ser tratado como tal.
        ){ navBackStackEntry ->
            val tareaId = navBackStackEntry.arguments?.getInt("tareaId") ?: 0
            EditarTareaScreen(
                navController = navController,
                tareaId = tareaId
            )
            //AgregarTareaScreen(navController = navController)
        }

        // ruta dinamica ya que siempre cambia de valor su parametro
        composable (
            route = "detalle/{id}"
        ){ navBackStackEntry ->
            val tareaId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable // return@composable sirve para que NO se muestre la pantalla en caso de que el ID no sea valido

            DetalleTareaScreen(
                tareaId =tareaId,
                navController = navController
            )
        }
    }
}