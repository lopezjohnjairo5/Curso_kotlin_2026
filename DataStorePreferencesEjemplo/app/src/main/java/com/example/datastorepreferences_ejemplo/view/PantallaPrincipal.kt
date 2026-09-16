package com.example.datastorepreferences_ejemplo.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.datastorepreferences_ejemplo.data.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun PantallaPrincipal(context : Context){
    val scope = rememberCoroutineScope() // para poder utilizar corrutinas, en este caso serán las que llamaran a las funciones suspendidas del dataStore

    val dataStore = remember { DataStoreManager(context) } // para acceder a los flows y a las funciones suspendidas

    val nombre by dataStore.nombreFlow.collectAsState(initial = "")
    val contador by dataStore.contadorFlow.collectAsState(initial = 0)
    val modoOscuro by dataStore.modoOscuroFlow.collectAsState(initial = false)

    var textoNombre by remember { mutableStateOf(nombre) }

    // se ejecuta en la primera composicion y cada vez que el flow cambie
    LaunchedEffect(nombre){
        textoNombre = nombre
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "nombre guardado: $nombre")
        Text(text = "Contador: $contador")
        Text(text = "Modo: $modoOscuro")

        Spacer(modifier = Modifier.height(16.dp))

        // actualizamos el nombre ingresado por teclado para almacenarlo
        OutlinedTextField(
            value = textoNombre,
            onValueChange = {
                textoNombre = it
                // cuando cambie el valor del campo de texto,
                // ejecute la corrutina Scope, la cual guarda el nombre puesto en el campo de texto
                scope.launch {
                    dataStore.guardarNombre(it)
                }
            },
            label = {Text(text="Ingresa un nombre")}
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // cuando se de clic en el boton,
                // ejecute la corrutina Scope,
                // la cual incrementa en 1 el valor del contador
                scope.launch {
                    dataStore.guardarContador(contador+1)
                }
            }
        ) {
            Text(text = "Incrementar contador")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Switch(
            checked = modoOscuro,
            onCheckedChange = {
                // cuando se deslice el switch,
                // ejecute la corrutina Scope,
                // la cual cambia el modo oscuro de false a true
                scope.launch {
                    dataStore.guardarModoOscuro(it)
                }
            }
        )

        Text(text = "Activar modo oscuro")

    }
}