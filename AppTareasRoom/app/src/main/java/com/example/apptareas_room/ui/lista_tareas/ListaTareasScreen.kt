package com.example.apptareas_room.ui.lista_tareas

//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTareasScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    navController: NavHostController
){
    val tareas by viewModel.tareas.collectAsState()
    var query by remember { mutableStateOf("") }// para almacenar el texto que ingresa el usuario en el campo de busqueda
    val tareasFiltradas by viewModel.tareasFiltradas.collectAsState()
    val tareasAMostrar = if(query.isEmpty()) tareas else tareasFiltradas

    val snackbarHostState = remember { SnackbarHostState() } // controla la visibilidad el snackbar
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mis tareas")
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("pantallaAjustes")
                    }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Ajustes"
                        )
                    }
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.buscarTareas(query)
                },
                label = { Text (text = "Buscar por título o descripción")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (tareasAMostrar.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "No hay tareas.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(tareasAMostrar){ tarea ->
                        TareaItem(
                            tarea = tarea,
                            onEliminarClick = { tareaSeleccionada ->

                                viewModel.eliminarTarea(tareaSeleccionada, query)
                                coroutineScope.launch {
                                    val resultado = snackbarHostState.showSnackbar(
                                        message = "Tarea sliminada",
                                        actionLabel = "Deshacer",
                                        duration = SnackbarDuration.Short
                                    )

                                    if (resultado == SnackbarResult.ActionPerformed){
                                        viewModel.restaurarTarea(query)
                                    }
                                }
                            },
                            onEditarClick = { tareaSeleccionada ->
                                navController.navigate("editarTareaScreen/${tareaSeleccionada.id}")
                            },
                            onVerDetalleClick = {tareaSeleccionada ->
                                navController.navigate("detalle/${tareaSeleccionada.id}")
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp)) // espacio general entre las tareas
                    }
                }
            }
        }
    }
}