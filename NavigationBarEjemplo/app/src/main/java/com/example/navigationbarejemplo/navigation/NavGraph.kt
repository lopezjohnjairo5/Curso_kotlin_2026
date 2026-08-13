package com.example.navigationbarejemplo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationbarejemplo.screens.PantallaAjustes
import com.example.navigationbarejemplo.screens.PantallaInicio
import com.example.navigationbarejemplo.screens.PantallaPerfil
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavGraph(
    navController: NavHostController,
    padding : PaddingValues,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
){
    NavHost(
        navController = navController,
        startDestination = "PantallaInicio",
        modifier = Modifier.padding(padding)
    ){
        composable ("PantallaInicio"){
            PantallaInicio(
                snackbarHostState,
                scope
            )
        }
        composable ("PantallaPerfil"){
            PantallaPerfil()
        }
        composable ("PantallaAjustes"){
            PantallaAjustes()
        }

    }
}