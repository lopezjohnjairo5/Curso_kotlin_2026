package com.example.mvvmejemplo.model

// usada para representar un modelo de datos, molde o plantilla
// los data class traen metodos utiles por eso se usan como plantilla
data class Producto(
    val id: String,
    val nombre: String,
    val precio: Double
)