package com.example.ciclodevida_viewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ciclodevida_viewmodel.screens.PantallaA
import com.example.ciclodevida_viewmodel.screens.PantallaB

// encargado de la navegacion entre pantallas
@Composable
fun AppNavHost(){
    val navController = rememberNavController() // preservamos el estado de la navegacion

    // contenedor de las pantallas
    NavHost(
        navController = navController,
        startDestination = "pantallaA"
    ){
      // establecemos las pantallas del proyecto
        composable("pantallaA"){
            PantallaA(navController)
        }
        composable("pantallaB"){
            PantallaB(navController)
        }
    }
}