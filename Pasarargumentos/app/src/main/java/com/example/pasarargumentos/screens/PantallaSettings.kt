package com.example.pasarargumentos.screens

import android.widget.Space
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
import com.example.pasarargumentos.navigation.PantallaDetalleRoute
import com.example.pasarargumentos.navigation.PantallaLoginRoute


@Composable
fun PantallaSettings(
    navController: NavHostController,
    alias: String

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla de Settings con $alias",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // 1. Definimos las variables con los datos reales que queremos enviar
                navController.navigate(PantallaLoginRoute){
                    popUpTo(0)
                }
            }
        ) {
            Text(text = "Ir a detalle")
        }

    }
}