package com.example.navigationbarejemplo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaAjustes(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Pantalla Ajustes",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ejemplo Badge- simula 3 notificaciones sin leer",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        BadgedBox( // es un componente de Jetpack Compose y Compose Multiplatform que sirve para mostrar una insignia o distintivo (badge) flotante
         badge = {
             Badge(
                 modifier = Modifier
                     .size(28.dp),
                 containerColor = Color(0XFF388E3C),
                 contentColor = Color.White
             ){
                 Text(
                     text = "3",
                     fontSize = 12.sp,
                     fontWeight = FontWeight.Bold,
                     style = MaterialTheme.typography.titleLarge
                 )
             }
         }
        ){
            // el badge muestra sobre el boton o imagen el numero 3, es algo visualmente informativo.
            Button(
                onClick = {}
            ) {
                // podria usarse solo el Icono sin el button
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    modifier = Modifier.size(48.dp)
                )

                //Text(text = "Notificaciones")
            }

        }
    }
}