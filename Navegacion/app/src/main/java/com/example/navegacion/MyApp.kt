package com.example.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.navigation.NavGraph
import com.example.navegacion.navigation.NavigationBarEjemplo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){
    // Listas con los textos y los íconos para cada una de las pestañas inferiores
    val items = listOf("Inicio", "Detalle", "Buscar", "Perfil", "Settings")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Badge,
        Icons.Default.Search,
        Icons.Default.Person,
        Icons.Default.Settings
    )
    // Al usar 'rememberSaveable', la pestaña seleccionada no se reinicia a 0 si el usuario
    // rota la pantalla o si el sistema destruye temporalmente la actividad por falta de memoria
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    val navController = rememberNavController() // creando el controlador de navegacion
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text="Navegacion en compose", color = MaterialTheme.colorScheme.onPrimary)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            // barras inferiores
            NavigationBarEjemplo(navController, items, icons, selectedItem, onChangeValue = { index -> selectedItem = index })

        },
    ) { paddingValues ->
        NavGraph( navController = navController, padding = paddingValues, valueSection = selectedItem,)
    }
}