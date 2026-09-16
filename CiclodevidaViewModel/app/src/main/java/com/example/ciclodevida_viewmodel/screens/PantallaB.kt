package com.example.ciclodevida_viewmodel.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ciclodevida_viewmodel.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaB(
    navController: NavController,
    viewModel: ProductoViewModel = viewModel() // helper
){
    val productos = viewModel.productos

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pantalla B - productos")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn{
                items(productos){ producto->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Id: ${producto.id}")
                            Text(text = producto.nombre)
                            Text(text = "Precio: ${producto.nombre} USD")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // retornamos a la pantalla anterior, en este caso la A
                    navController.popBackStack() // lleva a la pantalla anterior (A) y eliminamos a pantalla B cuando volvamos a pantalla A
                },
                modifier = Modifier.padding(paddingValues)
            ) {
                Text(
                    text = "Volver a pantalla A"
                )
            }
        }





    }
}