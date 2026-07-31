package com.example.ejemplomodilarizacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    selectedSections : String,
    onMenuClick : () -> Unit // funcion callback sin parametros ( () ) y Sin retorno (Unit)
){
    Scaffold(
        topBar = {
            // Barra superior de la aplicación
            TopAppBar(
                title = {
                    Text(
                        text = "Mi aplicacion",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    // El clásico botón de hamburguesa (Tres líneas)
                    IconButton(
                        onClick = onMenuClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú"
                        )
                    }
                },
                // Personalización de colores de la TopAppBar basándose en el tema actual
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        // El contenedor principal de nuestra vista de usuario.
        // Se le aplican los 'paddingValues' calculados por el Scaffold para que su contenido
        // se posicione exactamente debajo de la TopAppBar y no se esconda detrás de ella.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // paddingvalues = representa el espacio interno necesario para evitar que se quede cubierto por otros componentes como topappbar, bottomappbar, floatingactionbutton, entre otros.
            contentAlignment = Alignment.Center
        ) {
            // Muestra dinámicamente un texto con el nombre de la sección seleccionada en el menú lateral
            Text(text = "seccion: ${selectedSections}")
        }
    }
}