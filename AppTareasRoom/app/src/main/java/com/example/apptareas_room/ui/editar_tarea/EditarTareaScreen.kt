package com.example.apptareas_room.ui.editar_tarea

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apptareas_room.ui.viewmodel.TareaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarTareaScreen(
    navController : NavController,
    tareaId : Int
){
    val viewModel : TareaViewModel = hiltViewModel() // obtenemos una copia del navbackstack entry es decir del ciclo de vida de las pantallas // obtenemos una copia del navbackstack entry es decir del ciclo de vida de las pantallas
    val context = LocalContext.current
    val tarea by viewModel.tareaSeleccionada.collectAsState(initial = null)

    // estados locales iniciados como vacios
    var titulo by rememberSaveable{ mutableStateOf("") }
    var descripcion by rememberSaveable{ mutableStateOf("") }
    var datosCargados by remember{ mutableStateOf(false) } // bandera para evitar que los campos titulo y descripcion se vuelvan a llenar cada vez que la tarea cambie

    var completada by rememberSaveable{ mutableStateOf(false)} // switch para saber si se completó o no una tarea

    // se ejecuta una sola vez al cargar la pantalla
    // permite obtener la tarea solicitada para editar
    LaunchedEffect(Unit) {
        viewModel.cargarTareaPorId(tareaId)
    }

    // este launch se ejecuta despues de obtener la tarea gracias al launch anterior
    LaunchedEffect(tarea) {
        if(tarea != null && !datosCargados){
            titulo = tarea!!.titulo // !! asegura que NO sea nulo
            descripcion = tarea!!.descripcion ?: "" // si es nulo ponemos "" vacio
            completada = tarea!!.completada
            datosCargados = true

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text="Editar tarea") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = {titulo=it},
                label = { Text(text = "Titulo")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = {descripcion=it},
                label = { Text(text = "Descripción")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Text(text="¿Tarea completada?", modifier = Modifier.weight(1f))

                Switch(checked = completada, onCheckedChange = { isChecked ->
                    completada = isChecked
                    tarea?.let {
                        viewModel.actualizarTarea(
                            it.copy(completada = isChecked) // copia modificada de la tarea original
                        )
                    }
                })
            }

            Button(
                onClick = {
                    tarea?.let {
                        // si es nullo no se ejecuta por eso el tarea?
                        // actualizamos la tarea
                        viewModel.actualizarTarea(
                            it.copy(titulo=titulo, descripcion = descripcion, completada = completada)
                        )

                        Toast.makeText(
                            context,
                            "Tarea actualizada",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.popBackStack() // nos retorna a la ventana anterior
                    }
                }
            ) {
                Text(text="Actualizar tarea")
            }
        }
    }
}