package com.example.pasarargumentos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pasarargumentos.navigation.PantallaSettingsRoute

@Composable
fun PantallaDetalle(
    navController: NavHostController,
    nombreUsuario: String,
    edadUsuario: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 💡 Optimizaciones visuales y de código:
        Text(
            // En Kotlin no necesitas usar '.toString()' dentro de un String Template ${} si es un entero,
            // basta con poner directamente $edadUsuario.
            text = "Pantalla detalle. \n\nBienvenido $nombreUsuario \nTu edad es $edadUsuario",
            style = MaterialTheme.typography.titleLarge,
            // Agregamos alineación centrada para que los saltos de línea se vean ordenados en pantalla.
            textAlign = TextAlign.Center
        )
    }
    Button(
        onClick = {
            // 1. Definimos las variables con los datos reales que queremos enviar
            val alias = "@PepiGrillo"

            // 2. NAVEGACIÓN MODERNA
            // En lugar de concatenar un String ("pantallaDetalle/Pepe..."),
            // instanciamos directamente la data class que definimos en el NavGraph.
            navController.navigate(
                PantallaSettingsRoute(
                    alias = alias,
                )
            )
        }
    ) {
        Text(text = "Ir a settings")
    }
}
