package com.example.estados

import android.os.Bundle
import android.widget.Button
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estados.ui.theme.EstadosTheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstadosTheme {
                /*
                *
                *  STATE HOISTING
                *
                * Es un patron de Jetpack Compose que indica que los estados no deben ser creados y manejados por el mismo componente
                * es por esto que los componentes que requieran estados persitentes como remember o rememberSaveable deberan
                * recibir mediante parametro el estado y una funcion callback para trabajar con los estados y
                * el padre del composable será el encargado de crear el estado y pasarlo junto a la callback.
                *
                * En otras palabras el padre crea el patron y el hijo lo recibe por parametro
                * ver Ej: Padre - hijo
                *
                *
                * DERIVED STATE OF - ESTADO DERIVADO DE OTRO
                *
                * Es una funcion de Jetpack Compose usada para crear un estado
                * que deriva o depende de otro, se utiliza dentro de remember y devuelve un State,
                * esto indica que su valor se actualizará solo automaticamente cuando cambie el estado
                * del cual depende. Util cuando el calculo a realizar es pesado,
                * por ejemplo al hacer filtrados, sumatorias, búsquedas, etc.
                * ver Ej: EstadoDerivado
                *
                * */

                //EjemploRecordarEstado() // funcional, pero no recomendada, ya que la variable de estado la almacena el mismo componente. Ademas el valor del estado se pierde al girar pantalla.
                //ContadorScreen() // recomendada: en esta se llama al padre y este llama al hijo, y en el padre se declara el estado que luego se pasa al componente que lo usa, es decir el hijo
                //EjemploRecordarEstadoConGiroPantalla() // mantiene el valor del estado incluso al girar la pantalla
                //Padre() // ejemplo de state hoisting
                //EstadoDerivado() // ejemplo de un estado dependiente o derivado de otro
                //EstadoDerivado2() //ejemplo 2, muestra el cambio de color segun la cantidad de caracteres introducida
                //MiListaMutable() // ejercicio con lista mutable y manejo de estados
                //MiListaMutablePersistente() // ejercicio con lista mutable PERSISTENTE, es decir almacena los valores incluso al rotar la pantalla
                //MiMapaDeUsuariosMutable() // ejercicio con los valores de un mapa de datos mutable
                //MiMapaDeUsuariosMutablePersistente() // ejercicio con los valores de un mapa de datos mutable y persistencia
                //CounterCicloDeVida() // ejemplo de contador con mensaje por consola al inicializar un componente o mostrarlo por primera vez en pantalla
                ListadoDeTareas()
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
    *
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


@Composable
fun Padre(){
    var count by rememberSaveable { mutableIntStateOf(0) }
    var myText by rememberSaveable { mutableStateOf("")}

    // Creamos una columna
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // llamamos al componente hijo y le pasamos el estado y la funcion callback
        Hijo(count, onIncrement = { count++ })

        // llamamos al componente hijo2 y le pasamos el estado y la funcion callback
        Hijo2(myText, myCallback = { myText=it })
    }
}

@Composable
fun Hijo(count:Int, onIncrement: () -> Unit){
    // recibimos el estado y la callback function

    // al presionar el btn se incrementará el contador y se actualizará el texto del BTN
    Button(onClick = onIncrement) {
        Text("Click N° $count")
    }

}

@Composable
fun Hijo2(myTxt: String, myCallback: (String) -> Unit){

    Text(
        text = myTxt,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )

    // creamos un input para introducir texto
    OutlinedTextField(
        value = myTxt,
        onValueChange = myCallback,
        label = { Text("Introduzca un texto") },
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun EstadoDerivado(){
    /*
    aplicacion que indica si el numero del contador
    es par o impar
    El valor de par se ejecuta automaticamente,
    cada vez que cambia el valor del estado padre.
    */

    // una es var ya que esta cambiará su valor constantemente, la otra val ya que no deberia ser posible cambiar el valor directamente
    // solo debe cambiar su valor al cambiar el valor del estado padre
    var count by remember { mutableIntStateOf(0) } // estado "padre"
    val pair by remember { derivedStateOf { count % 2 == 0 } } // estado dependiente del anterior

    // componentes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contador: $count",
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Red,
                textDecoration = TextDecoration.Underline
            )
        ) // mostramos el Número del contador
        Text(text = if (pair) "Número Par" else "Número impar") // mostramos el resultado del calculo de par o impar
        Button(
            onClick = {count++}
        ){
            Text("Clic aquí")
        }
    }
}

@Composable
fun EstadoDerivado2(){
    /*
    * aplicacion que permite ingresar caracteres
    * y segun la cantidad cambia el color, recordar
    * que el estado Hijo solo se actualizará al cambiar el estado padre,
    * esto ayuda a mejorar el rendimiento de las aplicaciones
    * */
    var text by rememberSaveable { mutableStateOf("") }
    val textColor by remember{
        derivedStateOf {
            //if(text.length > 10) Color.Red else Color.Blue
            calculoLongitudTexto(text) // hace lo mismo que la linea anterior, sin embargo se separa la logica en una funcion aparte
        }
    } // debe ser remember y no rememberSaveable ya que de lo contrario se guardaria siempre el mismo valor

    // componentes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it},
            label = { Text("Introduzca un texto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Longitud: ${text.length}",
            color = textColor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        ) // mostramos el Número del contador
    }
}

fun calculoLongitudTexto(text: String): Color{
    /*
    * funcion encargada de calcular la longitud de un texto
    * y segun este, retornar un color acorde
    * */
    //if(text.length > 10)
    //    return Color.Red
    //return Color.Blue

    return when (text.length){
        in 0..10 -> Color.Yellow
        in 10..20 -> Color.Blue
        in 20..30 -> Color.Red
        in 30..40 -> Color.Green
        in 50..60 -> Color.Black
        in 60..70 -> Color.Magenta
        in 70..80 -> Color.Cyan
        in 80..100 -> Color.LightGray
        else -> Color.Gray
    }
}


@Composable
fun MiListaMutable() {
    /* al utilizar mutableStateListOf, NO se debe usar
    * rememberSaveable,
    * para esos casos se hace uso de Saver, ver ejemplo MiListaMutablePersistente
    *
    * */
    val itemList = remember { mutableStateListOf("v1","v2","v3","v4") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { itemList.add("v${itemList.size + 1}")}
        ){
            Text("Agregar nuevo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(itemList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    elevation = CardDefaults.cardElevation(4.dp) // sombra de la tarjeta
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item, style = MaterialTheme.typography.bodyLarge)

                        // boton de eliminacion de elemento
                        IconButton(
                            onClick = { itemList.remove(item) }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MiListaMutablePersistente() {
    /*
    * esta listaMutable SI será persistente, debido a que se utiliza
    * listSaver<SnapshotStateList<String>, String>
    * */
    val listSaver = listSaver<SnapshotStateList<String>, String>(
        save = {it.toList()}, // almacenamos el valor convirtiendolo en un formato compatible, en este caso a una lista normal
        restore = {it.toMutableStateList()} // lo convertimos a lista mutable en caso de un error
    )
    val itemList = rememberSaveable( saver = listSaver) { mutableStateListOf("v1","v2","v3","v4") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { itemList.add("v${itemList.size + 1}")}
        ){
            Text("Agregar nuevo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(itemList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    elevation = CardDefaults.cardElevation(4.dp) // sombra de la tarjeta
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item, style = MaterialTheme.typography.bodyLarge)

                        // boton de eliminacion de elemento
                        IconButton(
                            onClick = { itemList.remove(item) }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MiMapaDeUsuariosMutable(){

    /*
    * Esta funcion es un ejemplo de como se trabaja con Mapas mutables, (equivalentes a diccionarios en python),
    * en Kotlin existen tanto los mapas normales y los mapas mutables(mutableStateMapOf), la diferencia está en que los mapas
    * normales son de solo lectura, NO SON EDITABLES, es decir no se puede agregar, editar, eliminar elementos de
    * los mapas normales (mapOf), para este ejemplo el mutableStateMapOf no es persistente, por lo cual
    * al cambiar la orientacion de pantalla (rotacion: vertical, horizontal o Landscape, portrait) se reiniciará el mapa
    * o dicho de otra forma se reseteará el mapa
    * */
    val users = remember {
        mutableStateMapOf(
            1 to "John",
            2 to "Maria",
            3 to "Roberta"
        )
    }

    //Opcional: variables para tener nombres al azar para ponerlos al dar clic al btn de agregar
    val nombres = listOf("Armando","Ariel","Beatriz","Camilo","Doris","Edwin","Fabio","Godinez","Hector","Isabel","Julian","Kimberly","Lorena","Manuel","Nancy","Orlando","Patricia","Rosa","Sandra","Tatiana","Valentina","William","Ximena","Zamara")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val newId = (users.keys.maxOrNull() ?: 0) + 1
                var nombreAl = nombres.random() // selecciona un nombre al azar del listado anterior
                users[newId] = nombreAl
            }
        ) {
            Text(text="Agregar nuevo usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // convertir mapa en lista para visualizarlo
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Limita el alto a 300dp
        ){
            // con Items recorremos todoo el contenido de users
            items(users.toList()){ (id, name) ->
                // llamamos al componente que crea la tarjeta y le pasamos los datos de los usuarios y la funcion callback para eliminar al pulsar el btn del icono delete.
                UserItem(id=id, name=name, onDelete = {users.remove(id)})
            }
        }
    }
}

@Composable
fun UserItem(id:Int,name:String,onDelete:()->Unit){
    /**
     * Funcion encargada de crear una tarjeta
     * con los valores pasados por parametro
     * */

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // para poner sombra a la tarjeta
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // mostramos los datos del usuario en una columna y al lado ponemos el btn de eliminar
            Column{
                Text(text = "Id: $id", style = MaterialTheme.typography.bodyLarge)
                Text(text = name, style = MaterialTheme.typography.bodyMedium)
            }

            // ponemos un icono de eliminar a cada tarjeta
            IconButton(
                onClick = onDelete
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

// ejemplo de MAPA con persitencia

@Composable
fun MiMapaDeUsuariosMutablePersistente(){

    /*
    * Esta funcion es un ejemplo de como se trabaja con Mapas mutables, (equivalentes a diccionarios en python),
    * en Kotlin existen tanto los mapas normales y los mapas mutables(mutableStateMapOf), la diferencia está en que los mapas
    * normales son de solo lectura, NO SON EDITABLES, es decir no se puede agregar, editar, eliminar elementos de
    * los mapas normales (mapOf), para este ejemplo se usan rememberSaveable y mapSaver, los cuales permiten que
    * el estado sea persistente, es decir no se reinician los valores al rotar la pantalla
    * */
    val users = rememberSaveable(
        saver = mapSaver(
            // 1. Convertimos las llaves Int a String para cumplir con los requisitos de mapSaver
            save = { map -> map.mapKeys { it.key.toString() } },
            // restore: sirve para reconstruir y recuperar el estado de los datos cuando la pantalla se destruya (por ejemplo, al rotar el dispositivo).
            restore = { restoredMap ->
                mutableStateMapOf<Int, String>().apply {
                    // 2. Convertimos las llaves String de vuelta a Int al restaurar
                    restoredMap.forEach { (key, value) ->
                        if (value is String) {
                            put(key.toInt(), value)
                        }
                    }
                }
            }
        )
    ) {
        mutableStateMapOf(
            1 to "John",
            2 to "Maria",
            3 to "Roberta"
        )
    }

    /*
    * ¿Cuándo se ejecuta el bloque save?
    * - Se ejecuta únicamente cuando el sistema operativo está a punto de destruir la pantalla o
    *   pausar la actividad, y necesita congelar el estado para no perderlo.
    *   Esto ocurre en tres situaciones:
    *       - Cambios de configuración:
    *           El caso más común, como cuando el usuario rota la pantalla o cambia el idioma del sistema.
    *       - Destrucción por falta de memoria:
    *           Si dejas la aplicación en segundo plano para abrir un juego pesado, Android
    *           puede cerrar tu app para liberar memoria RAM. save se asegura de empaquetar
    *           tus datos antes de que eso pase.
    *       - Cambio de pestañas/Navegación:
    *           Si utilizas librerías de navegación de Compose y sales de la pantalla actual hacia
    *           otra, el sistema guarda el estado para cuando decidas regresar.
    *   Si estás usando la aplicación normalmente (agregando o eliminando usuarios
    *   sin rotar la pantalla), el código dentro de save se ignora por completo para
    *   ahorrar batería y procesamiento.
    *
    * ¿Cuándo se ejecuta el bloque restore?
    * - Se ejecuta exclusivamente una sola vez, justo cuando la pantalla se vuelve a crear
    *   desde cero después de haber sido destruida por alguno de los motivos anteriores.
    *   Si el sistema encuentra datos guardados por save, ejecuta restore para reconstruir tu mapa.
    *   Si es la primera vez que el usuario abre la aplicación (no hay nada guardado),
    *   restore no se ejecuta, y en su lugar se lee el bloque de código inicial con los
    *   tres usuarios por defecto (John, Maria, Roberta).
    *
    * Utilidad de restore:
    * 1. El ciclo completo de supervivencia de los datosPaso A (Rotación de pantalla):
    * - Android destruye la vista. mapSaver ejecuta el bloque save y convierte tu estado en
    *   un mapa compatible con el sistema (Map<String, Any>).
    * - Paso B (Recreación de pantalla):
    *   Android vuelve a levantar la vista desde cero.
    * - Paso C (Tu código vuelve a vivir): mapSaver ejecuta el bloque restore,
    *   toma el mapa guardado en el paso A y te lo entrega para que recrees tu objeto original.
    *
    * 2. Conversión y tipado de datos
    * - Android almacena los datos de forma genérica como tipos primitivos o básicos (String, Int, Boolean).
    *   El bloque restore te da el espacio para castear o convertir esos datos de vuelta a las clases
    *   específicas de tu aplicación (como tu mutableStateMapOf<Int, String>).
    *
    * 3. Evitar reiniciar los valores iniciales
    * - Si no existiera el bloque restore, cuando la pantalla se vuelva a dibujar, Jetpack Compose
    *   ignoraría lo que el usuario modificó y volvería a ejecutar el bloque por defecto de abajo:
    *   mutableStateMapOf(
            1 to "John",
            2 to "Maria",
            3 to "Roberta"
        )
    * En resumen:
    *   - Sin 'restore', siempre volverías a tener solo los 3 usuarios originales
    *   - saver es un mecanismo de emergencia y recreación. Mientras la pantalla esté visible y
    *     estable, permanece totalmente inactivo.
    * */

    //Opcional: variables para tener nombres al azar para ponerlos al dar clic al btn de agregar
    val nombres = listOf("Armando","Ariel","Beatriz","Camilo","Doris","Edwin","Fabio","Godinez","Hector","Isabel","Julian","Kimberly","Lorena","Manuel","Nancy","Orlando","Patricia","Rosa","Sandra","Tatiana","Valentina","William","Ximena","Zamara")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val newId = (users.keys.maxOrNull() ?: 0) + 1
                var nombreAl = nombres.random() // selecciona un nombre al azar del listado anterior
                users[newId] = nombreAl
            }
        ) {
            Text(text="Agregar nuevo usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // convertir mapa en lista para visualizarlo
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Limita el alto a 300dp
        ){
            // con Items recorremos todoo el contenido de users
            items(users.toList()){ (id, name) ->
                // llamamos al componente que crea la tarjeta y le pasamos los datos de los usuarios y la funcion callback para eliminar al pulsar el btn del icono delete.
                UserItem(id=id, name=name, onDelete = {users.remove(id)})
            }
        }
    }
}


@Composable
fun CounterCicloDeVida(){
    /**
     * LaunchedEffect recibe una clave, si dicha clave es Unit solo se ejecutará una vez
     * si dicha clave es otra y esta cambia durante la ejecucion del programa
     * se reiniciará LaunchedEffect con el nuevo valor de la clave
     * LaunchedEffect es util con una clave nueva cuando:
     * - se quiere cargar datos de una API
     * - reiniciar un temporizador
     * - detectar cambios en permisos y configuraciones del sistema
     * - reiniciar animaciones al cambio de una propiedad o valor
     */
    var count by remember { mutableIntStateOf(0) }

    //LaunchedEffect se ejecuta por primera y unica vez cuando se muestra este componente en pantalla
    LaunchedEffect(Unit) { // el argumento Unit indica que solo se ejecutará una vez
        println("Este mensaje se muestra por primera y unica vez")
    }
    println( "La pantalla se está recomponiendo" )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contador: $count",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { count++ }
        ) {
            Text(
                text = "Click para incrementar"
            )
        }
    }
}

// Ejemplo de claves para recomposicion
data class Tarea (val id: String, val descripcion: String)

fun ListaInicialDeTareas() : List<Tarea> {
    //UUID.randomUUID() crea un id unico para cada elemento
    /**
     * return listOf(
     *         Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Kotlin Jet Pack C"),
     *         Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Php"),
     *         Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Js"),
     *         Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Css3")
     *     )
     */

    return mutableListOf(
        Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Kotlin Jet Pack C"),
        Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Php"),
        Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Js"),
        Tarea(id = UUID.randomUUID().toString(), descripcion = "Estudiar Css3")
    )

}

@Composable
fun ListadoDeTareas(){

    // convertimos la lista de tareas de inmutable a mutable gracias a mutableStateOf, así podemos agregar, editar, eliminar elementos de la lista
    var tareas by remember { mutableStateOf(ListaInicialDeTareas())}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                tareas = tareas + Tarea(
                    id = UUID.randomUUID().toString(),
                    descripcion = "Nueva tarea"
                )
            },
            modifier = Modifier.height(48.dp)
        ){
            Text(
                text = "Agregar tarea"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items( tareas, key = {it.id} ){ tarea ->
                TareaItem(
                    tarea = tarea,
                    onEliminar = {tareaEliminar ->
                        tareas.filter{it.id != tareaEliminar.id}
                    }
                )
            }
        }
    }
}



@Composable
fun TareaItem(tarea: Tarea, onEliminar:(Tarea)->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tarea.id,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = tarea.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            IconButton(
                onClick = {onEliminar(tarea)}
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea"
                )
            }
        }
    }
}