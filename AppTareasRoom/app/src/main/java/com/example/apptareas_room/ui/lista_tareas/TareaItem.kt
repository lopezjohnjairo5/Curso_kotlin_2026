package com.example.apptareas_room.ui.lista_tareas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptareas_room.data.local.entity.TareaEntity

@Composable
fun TareaItem(
    tarea: TareaEntity,
    onEliminarClick : (TareaEntity) -> Unit // resive una funcion lambda
){
    var mostrarDialogo by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 24.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // titulo de la tarea
                //Text(text = tarea.titulo,style = MaterialTheme.typography.titleMedium)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // titulo de la tarea
                    Text(text = tarea.titulo,style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .width(90.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    )   {

                        IconButton(
                            onClick = {
                                //onEliminarClick(tarea)
                                mostrarDialogo = true
                            }

                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar tarea",
                                tint = Color.Red
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar tarea",
                                tint = Color.Blue
                            )
                        }
                    }

                }

                //
                Spacer(modifier = Modifier.height(8.dp))

                // descripcion de la tarea
                Text(
                    text = tarea.descripcion ?: "Sin descripción",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )


                Spacer(modifier = Modifier.height(24.dp))

                // muestra fecha de creacion de la tarea
                Text(
                    text = "Creada: ${tarea.fechaCreacion}",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

            }


            if (mostrarDialogo){
                AlertDialog(onDismissRequest = {
                    mostrarDialogo = false // cierra el cuadro de dialogo si se da clic fuera
                },
                    title = { Text(text = "¿Eliminar tarea?") },
                    text = { Text(text = "¿Está seguro(a)?") },
                    confirmButton = { TextButton(
                            onClick = {
                                onEliminarClick(tarea)
                                mostrarDialogo = false

                                // mostramos un mensaje indicando que se ha eliminado la tarea correctamente.
                                Toast.makeText(
                                    context,
                                    "Tarea eliminada correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        )  {
                            Text("Eliminar", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                mostrarDialogo = false
                            }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }

        }
    }
}