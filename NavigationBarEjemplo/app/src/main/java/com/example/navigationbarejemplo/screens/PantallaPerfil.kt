package com.example.navigationbarejemplo.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPerfil(){

    val context = LocalContext.current // contexto actual, identifica donde estamos

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Pantalla Perfil",
            style = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = {
                Toast.makeText(context, "Hola desde un toast", Toast.LENGTH_LONG).show()
            }
        ) {
            Text(text = "Mostrar toast")
        }
    }
}