package com.example.estados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estados.ui.theme.EstadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstadosTheme {
                //EjemploRecordarEstado() // funcional, pero no recomendada, ya que la variable de estado la almacena el mismo componente. Ademas el valor del estado se pierde al girar pantalla.
                //ContadorScreen() // recomendada: en esta se llama al padre y este llama al hijo, y en el padre se declara el estado que luego se pasa al componente que lo usa, es decir el hijo
                EjemploRecordarEstadoConGiroPantalla() // mantiene el valor del estado incluso al girar la pantalla
            }
        }
    }
}

@Composable
fun EjemploRecordarEstado(){

    /*
    * En esta funcion se almacena un estado (count), la idea es que el estado almacenado
    * se modifica mediante la pulsacion de un boton y luego automaticamente la parte visual
    * que hace uso de este estado se modifica en pantalla.
    *
    * Los estados en JetpackCompose permiten actualizar la parte visual solo por porciones
    * es decir solo cambia visualmente la seccion o secciones que hacen uso del estado,
    * esto ayuda a mejorar el rendimiento.
    *
    * Esta recomposicion o redibujado se hace gracias a que se crea una variable
    * envuelta en un estado mutable para que, cada vez que el valor cambie,
    * Compose "recomponga" (redibuje) el texto automáticamente: en este caso es:
    *
    * var count by remember { mutableIntStateOf(0) }
    *
    * la diferencia con una variable normal como esta:
    *
    * val contador = remember { mutableStateOf(0) }
    *
    * es que si decidieramos refrescar la pantalla, con una variable
    * normal, tendriamos que redibujar todoo lo que está en la pantalla,
    * pero con by remember compose se encarga de hacer el redibujado unicamente
    * donde se encuentre la variable con remember.
    *
    * La practica recomendada en JetPckCompose es que las variables a recordar
    * NO las maneje el componente que las utiliza, sino el componente padre de
    * estas. En este ejemplo se muestra que es posible que las maneje el mismo
    * componente, pero, esto no es lo recomendado.
    *
    * Acerca de By Remember:
    * - By = hace que se pueda acceder al contenido de la variable con tan solo llamarla, ejemplo: contador,
    *       sin by seria necesario llamarla haciendo uso de un metodo como por ejemplo contador.value, esto se
    *       debe a que sin BY se almacena en la variable un objeto remember, con by se almacena el valor del objeto remember
    * - Remember = Hace que la "caja" no se destruya ni se reinicie cuando la pantalla se vuelva a dibujar.
    * - mutableStateOf(0): Crea la "caja" que avisa a Compose cuando el valor cambia.
    *
    * En otras palabras Cuando se agrega by, le estamos diciendo a Kotlin: "Delegale el acceso a esta
    * variable al estado de Compose". Automáticamente, la variable se comporta como un entero común
    * (Int), eliminando la necesidad de escribir .value
    *
    * Importante:
    *
    * - El estado almacenado con remember Se resetea o vuelve al valor de inicializacion cuando se
    *   voltea el dispositivo, es decir cuando cambia la orientacion entre landscape y portrait y
    *   vicebersa o se realiza una interrupcion del proceso, etc. Si se requiere conservar el valor
    *   incluso en los casos anteriores, se debe hacer uso de rememberSaveable, esta ultima funcion/metodo
    *   permite almacenar los valores tanto si hay o no cambios de configuracion, rotaciones, interrupciones
    *   e incluso compartirlos entre pantallas.
    *
    * - Para usar BY es necesario tener las siguientes importaciones:
    *   import androidx.compose.runtime.getValue
    *   import androidx.compose.runtime.setValue
    * */
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Cantidad de estados almacenados: $count",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { count++ /* aqui incrementamos el valor cada vez que se pulsa el btn*/}
        ) {
            Text( text = "Clic para incrementar" )
        }
    }
}


// practica de remember recomendada
@Composable
fun ContadorScreen() {
    // El estado vive aquí arriba (Padre)
    var cantidad by remember { mutableStateOf(10) }

    ContadorContent(
        valor = cantidad,
        onIncrementar = { cantidad++ } // Pasamos la acción
    )
}

@Composable
fun ContadorContent(valor: Int, onIncrementar: () -> Unit) {
    // Este componente solo dibuja, no gestiona el estado (Hijo)
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Cantidad de estados almacenados: $valor")
        Button(onClick = onIncrementar) {
            Text("Clic para incrementar")
        }
    }
}


// ejemplo de recordar estados incluso con cambios de configuracion
@Composable
fun EjemploRecordarEstadoConGiroPantalla(){

    /*
    * En esta funcion se almacena un estado (count), la idea es que el estado almacenado
    * se modifica mediante la pulsacion de un boton y luego automaticamente la parte visual
    * que hace uso de este estado se modifica en pantalla.
    *
    * */
    var myName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Introduce tu nombre y gira la pantalla",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // campo para ingresar contenido por pantalla
        OutlinedTextField(
            value = myName,
            onValueChange = {myName = it}, // se actualiza el valor de la variable con el valor que se ingresa
            label = { Text( text = "Ingresa tu nombre")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { myName = "" /* aqui reseteamos el valor cada vez que se pulsa el btn*/}
        ) {
            Text( text = "Borrar nombre" )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Explicacion: \nUna vez escrito algo en el campo, esto permanecerá allí hasta que se borre manualmente o mediante el btn.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
