package com.example.flows_ejemplo

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
import com.example.flows_ejemplo.ui.theme.FlowsEjemploTheme
import com.example.flows_ejemplo.view.PantallaProductos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowsEjemploTheme {
                /*
                * - Sobre este ejercicio al usar view model, al rotar la pantalla no se pierde
                * la informacion.
                * - Hacemos uso de imagenes de internet mediante la URL de las
                * mismas, por lo cual es necesario poner en el manifest el permiso de uso
                * de INTERNET correspondiente = <uses-permission android:name="android.permission.INTERNET"/>,
                * de lo contrario tendremos un error.
                * - Para poder hacer uso de imagenes de internet se requiere la LIB COIL, esta debe ser agregada
                * tanto en lib.versions como en buil.gradle (Module:app)
                *
                *
                * ¿Qué es Kotlin Flow?
                * Es una estructura de Flujos de Kotlin en Android basada en corrutinas
                * que permite emitir, procesar y consumir múltiples valores de forma asíncrona.
                * Funciona como un flujo de datos reactivo.
                *
                * Características principales
                * - Asíncrono: Procesa datos sin bloquear el hilo principal.
                * - Múltiples valores: A diferencia de una corrutina normal que devuelve un solo resultado, Flow emite una secuencia de valores a lo largo del tiempo.
                * - Frío (Cold): Un Flow no empieza a emitir datos hasta que alguien se suscribe o lo recopila (collect).
                *
                * ¿Para qué sirve?
                * - Gestionar eventos de la interfaz de usuario.
                * - Escuchar cambios en bases de datos locales (como Room).
                * - Recibir respuestas de red de forma reactiva.
                * */
                PantallaProductos()
            }
        }
    }
}
