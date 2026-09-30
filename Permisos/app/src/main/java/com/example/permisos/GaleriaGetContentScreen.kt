package com.example.permisos

import android.net.Uri
import android.widget.Button
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleriaGetContentScreen(){

    val uriSaver : Saver<Uri? , String> = Saver(
        save = {uri -> uri?.toString() ?: ""}, // recibe el OBJ y lo convierte en string
        restore = {saved -> saved.takeIf {it.isNotEmpty()}?.let(Uri::parse)} // convierte el string a URI
    )

    // para guardar el estado de la imagen seleccionada
    var imageUri by rememberSaveable(stateSaver = uriSaver) { mutableStateOf(null) }

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Seleccionar imagen (GetContent)") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUri != null){
                // cargamos la imagen mediante la URI
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = "Sin imagen",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                    // MIME -> Multipurpose Internet Mail Extension
                    // Extensiones de correo de internet de proposito multiple
                    pickImage.launch("image/*")
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Elegir de galeria"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { imageUri = null },
                enabled = imageUri != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Eliminar imágen"
                )
            }
        }
    }
}