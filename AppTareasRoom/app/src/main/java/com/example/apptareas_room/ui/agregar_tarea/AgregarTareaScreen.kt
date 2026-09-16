package com.example.apptareas_room.ui.agregar_tarea

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.apptareas_room.data.local.entity.TareaEntity
import com.example.apptareas_room.ui.viewmodel.TareaViewModel
import com.example.apptareas_room.utils.obtenerFechaActual

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarTareaScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    navController: NavHostController
){
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    val context = LocalContext.current // para el toast

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Nueva tarea")
                }
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = {titulo = it},
                label = { Text(text="Ingrese un titulo")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = {descripcion = it},
                label = { Text(text="Ingrese la descripcion")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    if (titulo.isNotBlank()){
                        val nuevaTarea = TareaEntity(
                            titulo = titulo,
                            descripcion = descripcion,
                            fechaCreacion = obtenerFechaActual()
                        )

                        viewModel.insertarTarea(nuevaTarea)

                        // mostrar el toast con mensaje
                        Toast.makeText(
                            context,
                            "Nueva tarea agregada",
                            Toast.LENGTH_SHORT // duracion del toast
                        ).show() // muestra el toast en pantalla

                        navController.popBackStack() // una vez creada la tarea mandará automaticamente a la pantalla anterior osea lista de tareas
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Guardar")
            }
        }

    }
}