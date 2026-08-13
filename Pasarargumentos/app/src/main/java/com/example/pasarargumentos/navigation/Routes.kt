package com.example.pasarargumentos.navigation

import kotlinx.serialization.Serializable // 💡 Importante: Para marcar las rutas de navegación

// 1. DEFINICIÓN DE RUTAS (Type-Safe Navigation)
// Reemplazamos los viejos "strings" por objetos y clases fuertemente tipadas.

// Para pantallas sin argumentos, usamos un objeto estático sencillo.
@Serializable
object PantallaInicioRoute


@Serializable
object PantallaLoginRoute

@Serializable
object PantallaPerfilRoute

// Para pantallas con argumentos, usamos una data class. Las propiedades representan los datos.
@Serializable
data class PantallaDetalleRoute(
    val nombreUsuario: String,
    val edadUsuario: Int
)

@Serializable
data class PantallaSettingsRoute(
    val alias: String
)


