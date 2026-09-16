package com.example.apptareas_room.ui.lista_tareas

//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareas_room.ui.viewmodel.TareaViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTareasScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    navController: NavHostController
){
    val tareas by viewModel.tareas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mis tareas")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // al presionar llevará a la pantalla de agregar tarea
                    navController.navigate("pantallaAgregarTarea")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { paddingValues ->

        if (tareas.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
                ){
                Text(text = "No hay tareas.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(tareas){ tarea ->
                    TareaItem(
                        tarea = tarea,
                        onEliminarClick = { tareaSeleccionada ->
                            viewModel.eliminarTarea((tareaSeleccionada))

                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // espacio general entre las tareas
                }
            }
        }
    }
}