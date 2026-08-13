package com.example.navegacion.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationBarEjemplo(navController: NavController,items: List<String>, icons: List<ImageVector>, selectedItem: Int,  onChangeValue: (Int) -> Unit) {

    // 1. DEFINICIÓN DE DATOS Y ESTADOS

    // 2. CONTENEDOR DE LA BARRA INFERIOR
    NavigationBar(
        modifier = Modifier.fillMaxWidth(), // Se extiende horizontalmente por completo en la pantalla
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        tonalElevation = 3.dp, // Aplica el tinte de elevación estándar de Material 3
        windowInsets = NavigationBarDefaults.windowInsets // Evita colisiones con la barra de navegación del sistema Android
    ) {

        // 3. CONSTRUCCIÓN DINÁMICA DE LAS PESTAÑAS
        // Recorremos los ítems para generar un botón independiente por cada uno de ellos
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                label = { Text(text = label) },
                selected = selectedItem == index, // Verifica si el índice actual coincide con el estado seleccionado
                onClick = {
                    navController.navigate("pantalla${label}")
                    onChangeValue(index)
                }, // Actualiza el estado al hacer clic, provocando la recomposición visual

                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = label
                    )
                },
                alwaysShowLabel = true // Mantiene el texto de la pestaña visible en todo momento, esté seleccionada o no
            )
        }
    }
}