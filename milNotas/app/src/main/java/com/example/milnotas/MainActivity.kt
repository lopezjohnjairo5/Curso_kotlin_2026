package com.example.milnotas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.milnotas.ui.theme.MilNotasTheme
import kotlin.String

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MilNotasTheme {
                ContainerApp()
            }
        }
    }
}

// 1. Corregido el constructor: El primer parámetro no tenía el nombre correcto al llamarlo en la función notes()
data class MyNote(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val message: String
)

fun notes(): List<MyNote> {
    return listOf(
        MyNote(title = "Bienvenida", message = "Bienvenido a milNotas, la app que te permite llevar un registro de todas tus tareas y/o pendientes.")
    )
}

@Composable
fun ContainerApp() {
    var title by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() } // 1. Crear el FocusRequester, para dar el foco despues de agregar nuevo item

    // CORRECCIÓN 2: Forma correcta de inicializar una lista mutable observable en Compose
    val notesList = rememberSaveable { mutableStateListOf<MyNote>().apply { addAll(notes()) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "milNotas",
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titulo aquí") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Introduce aquí tu nota") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(7.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && message.isNotEmpty()){
                    val newNote = MyNote(
                        id = java.util.UUID.randomUUID().toString(),
                        title = title,
                        message = message
                    )
                    // añadimos el nuevo elemento al listado
                    notesList.add(newNote)

                    // limpiamos las variables
                    title = ""
                    message = ""

                    // damos foco al primer text - el de titulo
                    focusRequester.requestFocus()

                } else {
                    Toast.makeText(context , "Titulo y contenido no pueden estar vacios.", Toast.LENGTH_SHORT).show()
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Agregar nota")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Listado de tareas",
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }

            // CORRECCIÓN 1: El bloque items ahora está DENTRO de las llaves del LazyColumn
            items(notesList, key = { it.id }) { note ->
                CardNotes(
                    title = note.title,
                    message = note.message,
                    onDeleteClick = {
                        notesList.remove(note) // Remueve el elemento de la lista observable
                    }
                )
            }
        }
    }
}

@Composable
fun CardNotes(title: String, message: String, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Ajustado padding para que no se vea tan separado
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f) // Esto asegura que la columna ocupe el espacio sobrante y no empuje el botón
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message
                )
            }

            IconButton(
                onClick = onDeleteClick // CORRECCIÓN 3: Vinculamos la acción de eliminar aquí
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea"
                )
            }
        }
    }
}
