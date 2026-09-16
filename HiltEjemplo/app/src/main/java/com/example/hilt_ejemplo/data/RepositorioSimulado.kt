package com.example.hilt_ejemplo.data

import androidx.compose.material3.ListItem
import com.example.hilt_ejemplo.model.Usuario

// simula fuente de datos externa, como de una API y será inyectado con hilt
class RepositorioSimulado {
    fun obtenerUsuarios():List<Usuario>{
        return listOf(
            Usuario(
                id = 1,
                nombre = "Carlos Martinez",
                correo = "carma@gmail.com",
                edad = 22,
                imagenUrl = "https://cdn.pixabay.com/photo/2026/07/14/04/40/04-40-14-48_1280.jpg"),
            Usuario(
                id = 2,
                nombre = "Sofia Cardenas",
                correo = "soca@gmail.com",
                edad = 20,
                imagenUrl = "https://cdn.pixabay.com/photo/2026/08/14/21/53/21-53-34-959_1280.jpg"),
            Usuario(
                id = 3,
                nombre = "Mauricio Garcia",
                correo = "mago@gmail.com",
                edad = 26,
                imagenUrl = "https://cdn.pixabay.com/photo/2025/11/28/21/12/21-12-59-815_1280.png")
        )
    }
}