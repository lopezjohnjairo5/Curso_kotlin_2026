package com.example.navigationbarejemplo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PantallaInicio(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Pantalla Inicio",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = "Presione el boton para mostrar un snackbar")
        Button(
            onClick = {
                scope.launch {
                    val resultado = snackbarHostState.showSnackbar(
                        message = "Elemento eliminado",
                        actionLabel = "Deshacer",
                        duration = SnackbarDuration.Short
                    )
                    when(resultado){
                        SnackbarResult.ActionPerformed -> {
                            // si se presiona el btn deshacer ... hacer
                        }
                        SnackbarResult.Dismissed -> {
                            // si el snackbar desaparece por tiempo ... hacer
                        }
                    }
                }
            }
        ) {
            Text(text = "Mostrar snackbar")
        }
    }
}