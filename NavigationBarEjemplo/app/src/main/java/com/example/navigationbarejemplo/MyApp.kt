package com.example.navigationbarejemplo
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationbarejemplo.data.pantallaBottom
import com.example.navigationbarejemplo.navigation.NavGraph

@Composable
fun MyApp(){
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ){
                snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color(0xFF2E88E5),
                    contentColor = Color.White,
                    actionColor = Color.Yellow
                )
            }
        },
        bottomBar = {
            NavigationBar{
                // obtener pantalla actual en la que se encuentra el usuario
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                pantallaBottom.forEach { pantalla ->
                    NavigationBarItem(
                        selected = currentRoute == pantalla.ruta,
                        onClick = {
                            navController.navigate(pantalla.ruta) {
                                // Id de la primera pantalla definida en el NavHost
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true // guardamos el estado de la pantalla antes de sacarla de la PILA
                                }
                                // evita que se agregue otra copia de la misma pantalla si ya estamos en ella
                                launchSingleTop = true

                                // recuperamos el estado anterior de la pantalla (el guardado con saveState)
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = pantalla.icono,
                                contentDescription = pantalla.titulo
                            )
                        },
                        label = { Text(text = pantalla.titulo) }
                    )
                }
            }

        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            padding = paddingValues,
            snackbarHostState = snackbarHostState,
            scope = scope
        )
    }
}
