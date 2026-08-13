package com.example.navigationbarejemplo.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
// declaramos un data class
data class PantallaNav(
    val ruta : String, // ej: pantallaInicio
    val icono : ImageVector, // ej:Icons.Default.Home
    val titulo : String // ej: inicio
)

val pantallaBottom = listOf(
    PantallaNav("PantallaInicio", Icons.Default.Home, "Inicio"),
    PantallaNav("PantallaPerfil", Icons.Default.Person, "Perfil"),
    PantallaNav("PantallaAjustes", Icons.Default.Settings, "Ajustes")
)
