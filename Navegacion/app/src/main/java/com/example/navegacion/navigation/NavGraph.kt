package com.example.navegacion.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navegacion.screens.PantallaBuscar
import com.example.navegacion.screens.PantallaDetalle
import com.example.navegacion.screens.PantallaInicio
import com.example.navegacion.screens.PantallaPerfil
import com.example.navegacion.screens.PantallaSettings

@Composable
fun NavGraph(
    navController: NavHostController, // permite movernos entre pantallas incluye el historial
    padding: PaddingValues, // relleno interno de la pantalla para evitar que el contenido se dibuje debajo o encima de otros componentes
    valueSection : Int
){
    // NavHost muestra el contenedor de la pantalla indicada
    NavHost(
        navController = navController, // controlador de navegacion
        startDestination = "pantallaInicio", // primera pantalla a mostrar
        modifier = Modifier.padding(padding)
    ) {
        // rutas
        composable("pantallaInicio") {
            PantallaInicio(navController,valueSection) // ponemos el nav controller como ejemplo para ver que se puede cambiar de pagina con otros botones
        }

        composable("pantallaDetalle") {
            PantallaDetalle(valueSection)
        }

        composable("pantallaSettings"){
            PantallaSettings(valueSection)// no se pasa navController ya que aquí no navegamos a otra pantalla
        }

        composable("pantallaBuscar"){
            PantallaBuscar(valueSection)// no se pasa navController ya que aquí no navegamos a otra pantalla
        }


        composable("pantallaPerfil"){
            PantallaPerfil(valueSection)// no se pasa navController ya que aquí no navegamos a otra pantalla
        }
    }
}