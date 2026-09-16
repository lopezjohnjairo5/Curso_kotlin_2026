package com.example.viewmodel_remember_remembersaveable.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel_remember_remembersaveable.viewmodel.ContadorViewModel

@Composable
fun PantallaContador(
    // creamos automaticamente el viewmodel, por eso se asigna como valor por defecto
    viewModel: ContadorViewModel = viewModel()
){
    var contadorVolatil by remember { mutableIntStateOf(0) } // se guarda el valor en memoria sino se recarga o rota la pantalla
    var contadorPersistente by rememberSaveable { mutableIntStateOf(0) } // se guarda el valor incluso al rotar, pero no entre pantallas
    var contadorDesdeViewModel = viewModel.contador // se guarda el valor incluso entre pantallas

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Contador remember: ${contadorVolatil}")

        Button(
            onClick = {
                contadorVolatil++
            }
        ) {
            Text(text="Sumar remember")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text="Contador persistente: ${contadorPersistente}")

        Button(
            onClick = {
                contadorPersistente++
            }
        ) {
            Text(text="Sumar rememberSaveable")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text="Contador viewModel: ${contadorDesdeViewModel}")

        Button(
            onClick = {
                viewModel.sumar()
            }
        ) {
            Text(text="Sumar viewModel")
        }

        Button(
            onClick = {
                viewModel.restar()
            }
        ) {
            Text(text="Restar viewModel")
        }

    }
}