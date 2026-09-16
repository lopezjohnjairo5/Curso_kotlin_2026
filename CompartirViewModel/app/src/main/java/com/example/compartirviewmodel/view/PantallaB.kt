package com.example.compartirviewmodel.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compartirviewmodel.viewmodel.ContadorViewModel


@Composable
fun PantallaB(
    navController: NavHostController,
    sharedViewModel: ContadorViewModel // viewmodel compartido
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contador en pantalla B: ${sharedViewModel.contador}")
        Button(
            onClick = {
                sharedViewModel.decrementar()
            }
        ) {
            Text(text="Decrementar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("pantallaA")
            }
        ) {
            Text(text="Ir a Pantalla A")
        }
    }
}