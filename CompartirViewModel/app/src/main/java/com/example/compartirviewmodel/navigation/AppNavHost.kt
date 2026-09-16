package com.example.compartirviewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.compartirviewmodel.view.PantallaA
import com.example.compartirviewmodel.view.PantallaB
import com.example.compartirviewmodel.viewmodel.ContadorViewModel

@Composable
fun AppNavHost(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main" // pantalla principal o subgrafo con nombre main, (subgrafo es un conjunto de pantallas agrupadas que comparten la misma ruta raiz. Sirve para compartir el mismo viewmodel)
    ){
        // primera pantalla será pantalla A y pertenece al grupo main
        navigation(
            startDestination = "pantallaA",
            route = "main"
        ){
            /*
            * primero se accede a la pantallaA
            *
            * */
            composable(route = "pantallaA"){ navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry){
                    navController.getBackStackEntry("main") //agrupamos dentro de main a la pantalla
                }
                // obtenemos el ciclo de vida del grafo de navegacion para saber si podemos obtener el viewmodel
                val lifecycle = parentEntry.lifecycle
                val currentState = lifecycle.currentStateAsState().value

                // si el estado actual del ciclo de vida es Creado o superior, ejecutamos...
                if(currentState.isAtLeast(Lifecycle.State.CREATED)){
                    // obtenemos el view model para compartirlo
                    val sharedViewModel : ContadorViewModel = viewModel(
                        viewModelStoreOwner = parentEntry // define a quien pertenece el viewmodel, en este caso a la entrada del grafo
                    )

                    // invocamos la pantallaA y pasamos los parametros necesarios
                    PantallaA(
                        navController,
                        sharedViewModel
                    )
                }
            }
            /*
            * segundo se accede a la pantallaB
            *
            * */
            composable(route = "pantallaB"){ navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry){
                    navController.getBackStackEntry("main") //
                }
                // obtenemos el ciclo de vida del grafo de navegacion para saber si podemos obtener el viewmodel
                val lifecycle = parentEntry.lifecycle
                val currentState = lifecycle.currentStateAsState().value

                // si el estado actual del ciclo de vida es Creado o superior, ejecutamos...
                if(currentState.isAtLeast(Lifecycle.State.CREATED)){
                    // obtenemos el view model para compartirlo
                    val sharedViewModel : ContadorViewModel = viewModel(
                        viewModelStoreOwner = parentEntry // define a quien pertenece el viewmodel, en este caso a la entrada del grafo
                    )

                    // invocamos la pantallaB y pasamos los parametros necesarios
                    PantallaB(
                        navController,
                        sharedViewModel
                    )
                }
            }

        }


    }

}