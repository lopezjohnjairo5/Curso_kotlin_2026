package com.example.pasarargumentos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pasarargumentos.navigation.PantallaDetalleRoute // 💡 Importamos la ruta que creamos en el NavGraph

@Composable
fun PantallaInicio(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla de Inicio",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // 1. Definimos las variables con los datos reales que queremos enviar
                val nombre = "Pepe toño macias"
                val edad = 28 // 💡 Agregamos la edad que faltaba en tu código anterior

                // 2. NAVEGACIÓN MODERNA
                // En lugar de concatenar un String ("pantallaDetalle/Pepe..."),
                // instanciamos directamente la data class que definimos en el NavGraph.
                navController.navigate(
                    PantallaDetalleRoute(
                        nombreUsuario = nombre,
                        edadUsuario = edad
                    )
                )
            }
        ) {
            Text(text = "Ir a detalle")
        }
    }
}
