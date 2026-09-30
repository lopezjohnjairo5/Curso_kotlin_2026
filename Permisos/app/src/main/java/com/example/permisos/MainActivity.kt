package com.example.permisos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.permisos.ui.theme.PermisosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PermisosTheme {
                // ejemplo de solicitud de permisos -- acceso a camara
                //PantallaPrincipal()
                //CamaraScreen() // ejemplo de tomar foto con la camara
                //GaleriaGetContentScreen() // ejemplo de seleccionar imagen de galeria
                GaleriaPhotoPicker()
            }
        }
    }
}

