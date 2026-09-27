package com.example.permisos

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import java.io.File
import kotlin.contracts.contract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CamaraScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var tienePermiso by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // creamos la URI o ruta segura como string y la almacenamos aqui en fotoUritring
    var fotoUriString by rememberSaveable { mutableStateOf<String?>(null)}
    var fotoUri : Uri? = remember(fotoUriString){ fotoUriString?.let(Uri::parse)} // remember observa los cambios, sino cambia permanece el valor por default null, en caso contrario almacena la URI en forma de string
    var fotoFilePath by rememberSaveable { mutableStateOf<String?>(null) } // ruta completa del archivo fisico

    var pendingPhotoFile by remember { mutableStateOf<File?>(null) } // ruta fisica del archivo temporal

    var pendingPhotoUri by remember { mutableStateOf<Uri?>(null) } // ruta segura que se pasará para usarla en el almacenamiento de la imagen

    val authority = "com.example.permisos.provider" //

    LaunchedEffect(Unit) {
        tienePermiso = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    val abrirCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { exito : Boolean ->
            val currentPendingFile = pendingPhotoFile
            val currentPendingUri = pendingPhotoUri

            // si se tomó la foto bien y si ademas tenemos la ruta segura para almacenar dicha foto, entonces...
            if (exito && currentPendingUri != null && currentPendingFile != null){
                val oldFilePath = fotoFilePath
                fotoUriString = currentPendingUri.toString()
                fotoFilePath = currentPendingFile.absolutePath

                if(!oldFilePath.isNullOrEmpty() && oldFilePath != currentPendingFile.absolutePath){
                    runCatching {
                        File(oldFilePath).takeIf { it.exists() }?.delete()
                    }
                }
            } else {
                if (currentPendingFile?.exists() == true){
                    runCatching {
                        currentPendingFile.delete()
                    }
                }

                scope.launch {
                    snackbarHostState.showSnackbar("No se tomó ninguna foto")
                }
            }

            // falle o sea exitoso el guardado de la imagen eliminamos el valor almacenado en las variables
            pendingPhotoFile = null
            pendingPhotoUri = null
        }
    )

    val pedirPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { concedido : Boolean ->
            tienePermiso = concedido

            if (concedido){
                val (file, uri) = crearArchivoYUriSeguras(context, authority)
                pendingPhotoFile = file
                pendingPhotoUri = uri

                abrirCamara.launch(uri)

            } else {
                scope.launch { snackbarHostState.showSnackbar("Permiso de cámara denegado.") }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text( text = "Foto con TakePicture")}
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (tienePermiso){

                        val (file, uri) = crearArchivoYUriSeguras(context, authority)
                        pendingPhotoFile = file
                        pendingPhotoUri = uri
                        abrirCamara.launch(uri)
                    } else {
                        pedirPermiso.launch(Manifest.permission.CAMERA)
                    }
                }
            ) {
                Text( text = "Tomar foto")
            }

            Spacer(modifier = Modifier.height(16.dp))

            fotoUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Foto tomada",
                    modifier = Modifier.size(250.dp).clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    if( !fotoFilePath.isNullOrEmpty()){
                        kotlin.runCatching {
                            File(fotoFilePath!!).takeIf { it.exists() }?.delete()
                        }
                    }

                    fotoUriString = null
                    fotoFilePath = null

                    scope.launch { snackbarHostState.showSnackbar("Foto eliminada") }
                }
            ) {
                Text(text = "Borrar foto")
            }
        }
    }
}


private fun crearArchivoYUriSeguras(
    context: android.content.Context,
    authority: String
) : Pair<File, Uri>{
    val file = File.createTempFile("foto_","jpg", context.cacheDir)
    val uri = FileProvider.getUriForFile(context, authority, file)

    return file to uri
}