package com.example.componentes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentes.ui.theme.ComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComponentesTheme {
                MisComponentes() // pantalla que muestra algunos de los diferentes componentes disponibles

            }
        }
    }
}

@Composable
fun MisComponentes(){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /* campos de texto */
        TextFieldEjemplo()
        OutlinedTextFieldEjemplo()

    }
}


@Composable
fun TextFieldEjemplo(){
    /*
    * Es un componente de entrada de texto
    *
    * Este ejemplo muestra un campo de texto
    * en el cual se podrá introducir caracteres,
    * dicho campo es unilinea y tiene un maximo de 10 caracteres
    * al ser superados se muestra un icono de error,
    * se pone un borde rojo y se muestra un mensaje de error.
    * Ademas si se usa el teclado al presionar el boton Done (el chulo de confirmacion)
    * se muestra un toast (mensaje emergente) con un mensaje.
    *
    * */

    /* variables */
    var text by remember { mutableStateOf("") }
    val isError = text.length >= 10 // longitud maxima permitida para el campo
    val context = LocalContext.current // usado por el Toast

    TextField(
        value = text,
        onValueChange = { text = it}, // cuando el texto cambie actualizamos el estado con el valor introducido
        label = { Text(text = "Nombre:") },
        placeholder = {Text(text = "Ingrese su nombre")},
        leadingIcon = { // permite colocar un icono al principio de un componente, en este caso del textfield
            Icon(Icons.Default.Person, contentDescription = "icono persona")
        },
        trailingIcon = { // Su función es colocar un icono en el extremo derecho del campo de texto. Icono de error en este caso
            // este icono se muestra solo en caso de que exista un error, dicho error se genera si se introducen mas de 10 caracteres
            if(isError){
                Icon(Icons.Default.Warning, contentDescription = "icono warning", tint = MaterialTheme.colorScheme.error)
            }
        },
        isError = isError, // estos isError son diferentes el primero es una propiedad de TextField y la otra es una variable, basicamente mostramos un borde rojo si existe un error.
        singleLine = true, // solo permitimos que se introduzca una linea de texto
        keyboardOptions = KeyboardOptions(
            // permite controlar qué tipo de teclado se muestra y cómo reacciona ante la escritura del usuario.
            // capitalizamos cada una de las palabras que se escriban
            capitalization = KeyboardCapitalization.Words,
            autoCorrectEnabled = true, // habilita la autocorrecion automatica
            keyboardType = KeyboardType.Text, // indicamos el tipo de teclado a utilizar
            imeAction = ImeAction.Done // define el boton del teclado virtual, en la parte inferior derecha. Puede cambiarse a "Siguiente" (Next), "Ir" (Go), "Buscar" (Search) o "Listo" (Done).
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                /* aqui se pone lo que hace el boton de Done o hecho creado en la line aanterior */
                Toast.makeText(
                    context,
                    "Esto se ve al presionar el btn DONE",
                    Toast.LENGTH_LONG
                ).show()
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )

    if (isError){
        Text(
            text = "Maximo 10 caracteres",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 5.dp)
        )
    }

}

@Composable
fun OutlinedTextFieldEjemplo(){
    /*
    * Es un componente de entrada de texto, altamente personalizable,
    * a diferencia del TextField este tiene un borde contorneado(outline) alrededor.
    *
    * Permite manejar entradas de texto tanto simples como avanzadas, ej:
    * contraseñas, validaciones, estados de error, etc.
    *
    * Tiene multiples propiedades
    * - value = texto del campo
    * - onValueChange = se ejecuta al cambiar el value
    * - Label = eqiqueta flotante que se muestra en el campo y que se desplaza
    * - Placeholder = texto que se muestra mientras el usuario no ha escrito nada.
    * - leadingIcon = icono al inicio del campo de texto
    * - trailingIcon = icono al final del campo de texto, usado para validaciones por ejemplo.
    * - isError = booleano para saber si el campo esta en estado de error
    * - keyboardOptions = opciones del teclado como tipo de entrada (texto, email, etc)
    * - visualTransformation = transforma visualmente el texto, sirve para ocultar contraseñas
    * - singleLine = determina si es un campo multilinea o no
    * - modifier = permite aplicar modificaciones visuales y de comportamiento como padding, tamaño y alineacion
    * - shape = permite dar forma al campo como bordes redondeados (RoundedCornerShape)
    * - colors = permite personalizar los colores usando TextFieldDefaults.outlinedTextFieldColors()
    *
    *  este es el mismo ejemplo que el de TextField pero realizado con OutlinedTextField
    * */

    var text by remember { mutableStateOf("") }
    val isError = text.length >= 10 // longitud maxima permitida para el campo
    val context = LocalContext.current // usado por el Toast


    OutlinedTextField(
        value = text,
        onValueChange = { text = it }, // cuando el texto cambie actualizamos el estado con el valor introducido
        label = { Text(text = "Apellido:") },
        placeholder = {Text(text = "Ingrese su apellido")},
        leadingIcon = { // permite colocar un icono al principio de un componente, en este caso del textfield
            Icon(Icons.Default.Person, contentDescription = "icono persona")
        },
        trailingIcon = { // Su función es colocar un icono en el extremo derecho del campo de texto. Icono de error en este caso
            // este icono se muestra solo en caso de que exista un error, dicho error se genera si se introducen mas de 10 caracteres
            if(isError){
                Icon(Icons.Default.Warning, contentDescription = "icono warning", tint = MaterialTheme.colorScheme.error)
            }
        },
        isError = isError, // estos isError son diferentes el primero es una propiedad de TextField y la otra es una variable, basicamente mostramos un borde rojo si existe un error.
        singleLine = true, // solo permitimos que se introduzca una linea de texto
        keyboardOptions = KeyboardOptions.Default.copy( // permite controlar qué tipo de teclado se muestra y cómo reacciona ante la escritura del usuario.
            // capitalizamos cada una de las palabras que se escriban
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text, // indicamos el tipo de teclado a utilizar
            imeAction = ImeAction.Done, // define el boton del teclado virtual, en la parte inferior derecha. Puede cambiarse a "Siguiente" (Next), "Ir" (Go), "Buscar" (Search) o "Listo" (Done).
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                /* aqui se pone lo que hace el boton de Done o hecho creado en la line aanterior */
                Toast.makeText(
                    context,
                    "Esto se ve al presionar el btn DONE",
                    Toast.LENGTH_LONG
                ).show()
            }
        ),
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(12.dp), // ponemos los bordes redondeados
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary, // color del borde al enfocar o recibir el foco
            unfocusedTextColor = MaterialTheme.colorScheme.outline,  // color del borde al NO estar enfocado
            errorBorderColor = MaterialTheme.colorScheme.error,  // color del borde cuando hay un error
            focusedLabelColor = MaterialTheme.colorScheme.primary,  // color de la etiqueta al estar enfocada
            errorLabelColor = MaterialTheme.colorScheme.error  // color de la etiqueta en error.
        )
    )

    if (isError){
        Text(
            text = "Maximo 10 caracteres",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 5.dp)
        )
    }
}