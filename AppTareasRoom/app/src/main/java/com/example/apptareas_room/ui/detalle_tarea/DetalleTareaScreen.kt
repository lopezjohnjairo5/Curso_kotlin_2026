package com.example.apptareas_room.ui.detalle_tarea

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apptareas_room.R
import com.example.apptareas_room.ui.viewmodel.TareaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleTareaScreen(
    tareaId : Int,
    navController : NavController
){
    val viewModel : TareaViewModel = hiltViewModel()
    val tarea by viewModel.tareaSeleccionada.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.cargarTareaPorId(tareaId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text="Detalle de la tarea")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }// cuando se presione el icono enviará a la pantalla anterior
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // se muestra la columna con el contenido, pero, si aun esta cargando se mostrará el Box
        tarea?.let {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id= R.drawable.book),
                    contentDescription = "Icono libro",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ){
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        // titulo de la tarea
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(Icons.Default.Star, contentDescription = "Titulo", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text="Titulo:", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(it.titulo, style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(16.dp))

                        // descripcion de la tarea
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Star, contentDescription = "Descripcion", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Descripcion", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = it.descripcion ?: "Sin descripcion", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        // fecha de registro
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.DateRange, contentDescription = "Fecha de registro", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Fecha de registro", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = it.fechaCreacion, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))


                        // estado de la tarea
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Estado", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Estado", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = if(it.completada) "✅ Completada" else "⏳ Pendiente",
                            color = if(it.completada) Color(0xFF388E3C) else Color(0xFFF57C00),
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
                //Text(text = "Titulo: ${it.titulo}")
                //Text(text = "Descripción: ${it.descripcion}")
                //Text(text = "Estado: ${if (it.completada) "Completada" else "Pendiente" }")
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
}