package com.example.eventosunidireccionales

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
import com.example.eventosunidireccionales.ui.theme.EventosUnidireccionalesTheme
import com.example.eventosunidireccionales.view.PantallaPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventosUnidireccionalesTheme {
                /*
                * Los eventos de un solo uso
                * Son eventos UI unidireccionales, son acciones que deben
                * emitirse una sola vez desde el ViewModel hacia la interface de usuario
                *
                * Ejemplos:
                *
                * - mostrar un Snackbar
                * - Abrir un Dialog
                * - Hacer una navegacion
                * - Mostrar un mensaje de error
                *
                * Se les conoce como unidireccionales ya que el flujo va
                * en una sola direccion, va desde viewmodel -> interface de usuario (UI)
                * El ViewModel emite un evento, la UI lo escucha y reacciona,
                * dicho evento NO se almacena ni se vuelve a emitir,
                * esto se usa para ACCIONES QUE NO DEBEN PERSISTIR en el estado de la UI.
                *
                * Para los eventos de 1 solo uso se utiliza SharedFlow ya que este NO guarda el
                * estado y solo emite valores cuando existe un observador
                * activo.
                *
                * */
                PantallaPrincipal()
            }
        }
    }
}
