package com.example.permisos

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat

@Composable
fun PantallaPrincipal(){
    val context = LocalContext.current
    var estado by remember { mutableStateOf("No solicitado") }

    // gestionamos, el permiso
    val pedirPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { concedido : Boolean ->
            estado = if (concedido) "Concedido" else "Denegado"
        }
    )

    LaunchedEffect(Unit) {
        val granted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        // granted puede tomar 2 valores como resultado = PackageManager.PERMISSION_GRANTED = 0 ó PackageManager.PERMISSION_DENIED = -1 es decir True o False por la comparacion ==

        estado = if(granted) "Concedido" else "No solicitado"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Estado: $estado")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                pedirPermiso.launch(Manifest.permission.CAMERA)
            }
        ) {
            Text(text="Solicitar permiso cámara")
        }
    }
}