package com.example.ejemplomodilarizacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DrawerHeader(){
    // Encabezado del Perfil de Usuario
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Imagen representativa del usuario (Icono genérico estilizado en círculo)
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "foto de perfil",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            tint = MaterialTheme.colorScheme.primary
        )

        // Texto de bienvenida principal
        Text(
            text = "Bienvenido(a)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Texto secundario con el correo electrónico del usuario
        Text(
            text = "usuario@gmail.com",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

}