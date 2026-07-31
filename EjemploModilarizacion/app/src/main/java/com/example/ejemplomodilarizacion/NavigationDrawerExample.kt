package com.example.ejemplomodilarizacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerEjemplo() {

    // 1. GESTIÓN DE ESTADOS Y CONTROLADORES
    // Mantiene el estado del Drawer (Cerrado por defecto) y sobrevive a las recomposiciones
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Necesario para disparar las funciones suspendidas .open() y .close() en respuesta a eventos de clic
    val scope = rememberCoroutineScope()

    // Listas de datos que alimentarán la estructura del menú de manera dinámica
    val drawerItems = listOf("Inicio", "Buscar", "Perfil", "Favoritos", "Ajustes")
    val drawerIcons = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Person,
        Icons.Default.Favorite,
        Icons.Default.Settings
    )

    // Estado que recuerda el índice del ítem actualmente seleccionado para cambiar el contenido de la pantalla
    var selectedItem by remember { mutableIntStateOf(0) }

    // 2. CONTENEDOR RAÍZ DE LA INTERFAZ
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true, // Permite la interacción mediante gestos táctiles (Swipe)
        drawerContent = {
            DrawerContent(
                items = drawerItems,
                icons = drawerIcons,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    // funcion lambda para cerrar el drawer
                    selectedItem = index
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        // 3. CUERPO O CONTENIDO DE LA PANTALLA
        // Se renderiza por debajo del Drawer y reacciona a los cambios de 'selectedItem'
        MainScaffold(
            selectedSections = drawerItems[selectedItem],
            onMenuClick = {
                scope.launch { drawerState.open() }
            }
        )
    }
}