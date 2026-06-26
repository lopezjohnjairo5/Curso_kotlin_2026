package com.example.botonera

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.botonera.ui.theme.BotoneraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BotoneraTheme {
                MiBotonera() // recomendada para crear apps

            }
        }
    }
}

@Composable
fun MiBotonera() {
    Scaffold(
        // 1. Colocamos la fila directamente en el espacio reservado del Scaffold
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(Color.LightGray)
                    .navigationBarsPadding(), // evita que se superpongan los btns de navegacion
                horizontalArrangement = Arrangement.Center
            ) {
                // Tus botones conservan su Modifier.weight(1f) sin cambios, NO SE PONE TEXTO debido a que no cabrian en la pantalla correctamente
                MiBoton("", modifier = Modifier.weight(1f), Icons.Default.Key)
                MiBoton("", modifier = Modifier.weight(1f), Icons.Default.AccessTime)
                MiBoton("", modifier = Modifier.weight(1f), Icons.Default.Add)
                MiBoton("", modifier = Modifier.weight(1f), Icons.Default.AddAlert)
                MiBoton("", modifier = Modifier.weight(1f), Icons.Default.AddLocation)
            }
        }
    ) { innerPadding -> // 2. El Scaffold te entrega aquí los márgenes seguros generados

        // 3. El contenedor del contenido principal debe consumir esos márgenes
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Evita que el contenido se meta debajo de la botonera inferior
        ) {
            // Tu contenido principal va aquí (Textos, Listas, Imágenes, etc.)
            MiTexto()
            Espaciador()
            BarraCarga()
            MiCanvas()

        }
    }
}

@Composable
fun BarraCarga(){
    /*
    * muestra una barra de carga,
    * puede ser infinita o no
    * */
    LinearProgressIndicator(
        //modifier = Modifier.fillMaxWidth(),
        modifier = Modifier
            .width(150.dp)
            .background(MaterialTheme.colorScheme.secondary),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun MiCanvas(){
    /*
    * creamos un lienzo en blanco
    * para dibujar sobre él,
    * en el caso de este ejemplo un circulo
    * */
    val backgroundColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .size(300.dp) // esta medida se usa en radius al dibujar
            .padding(15.dp)
    ) {
        // dibujando un circulo
        drawCircle(
            color = backgroundColor,
            radius = size.minDimension/4, // tamaño definido en el canvas dividido en 4
        )

    }
}


@Composable
fun MiTexto(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // <--- ¡ESTO SALVA TUS CLICS!,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Este es mi primer texto en Kotlin Jetpack Compose. Ahora debug por wifi",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(text = "Este es un nuevo texto",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Thin,
            textAlign = TextAlign.Right,
            textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily.Monospace
        )
        Text(text = "Este es otro texto pero con style",
            style = TextStyle(
                color = Color.Magenta,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        // este texto cambia los colores segun el fondo de pantalla y segun si esta o no habilitado el modo oscuro en el dispositivo.
        Text(text = "Este es otro texto pero con styles de android",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // modificamos los valores por defecto que proporciona android studio
        Text(text="\nEste es un texto con los valores por defecto modificados.",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Thin,
                fontSize = 12.sp
            ),
            color = MaterialTheme.colorScheme.secondary
        )

    }
}


@Composable
fun Espaciador(
    h: Dp = 30.dp
){
    /*
    * permite separar elementos mediante un espacio en blanco
    * no es visible en si mismo.
    * NOTA: NO SIRVE EN EL INTERIOR DE UNA BOX, esto se debe a que la box superpone los elementos
    * como si estubieran con position:absolute (simil con css).
    * */
    Spacer(
        modifier = Modifier.height(h)
    )
}


@Composable
fun MiBoton(
    texto: String = "Boton 3",
    modifier: Modifier = Modifier, // Recibe el modifier del padre
    icon: ImageVector = Icons.Default.AccountBox
){
    OutlinedButton(
        onClick = {/* acciones al click */},
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier
        /*
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(0.dp))*/
    ) {
        Icon(icon, contentDescription = "Nuevo icono para btn")
        Text(texto)
    }
}
