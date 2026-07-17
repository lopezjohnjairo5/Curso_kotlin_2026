package com.example.efectossecundarios

import android.R
import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.efectossecundarios.ui.theme.EfectosSecundariosTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EfectosSecundariosTheme {
                /**
                 * Los efectos secundarios en kotlin Jetpack Compose son
                 *  operaciones que modifican el estado fuera
                 *  del ámbito de una función, por ejemplo consultar APIS,
                 *  conectar con BASES DE DATOS, uso de elementos fisicos del dispositivo como
                 *  sensores, etc.
                 *  Para gestionar estas acciones de forma segura, Kotlin y Android
                 *  utilizan constructores especializados:
                 *  - LaunchedEffect: Se utiliza para ejecutar operaciones asíncronas (como llamar a una base de datos,
                 *                      a un servicio web, o ejecutar animaciones) que necesita iniciarse cuando un componente entra en la composición.
                 *  - DisposableEffect: Se emplea para efectos secundarios que requieren un mecanismo de limpieza
                 *                      o cancelación cuando el componente sale de la pantalla.
                 *  - rememberCoroutineScope: Permite crear y lanzar corrutinas atadas al ciclo de vida del componente,
                 *                      ideales para ejecutar eventos provocados por el usuario (como mostrar un SnackBar, navegar, respuestas a clicks,
                 *                      eventos, gestos y demás interacciones del usuario).
                 *
                 * NOTA: la diferencia principal entre LaunchedEffect y rememberCoroutineScope, es que
                 *      LaunchedEffect se cancela y reinicia automaticamente al cambiar sus claves, pero, rememberCoroutineScope mantiene el mismo CoroutineScope lo que le permite lanzar las corutinas solo cuando el usuario lo requiera
                 *
                 *
                 *
                *
                ===================================================================================================================================================
                TABLA COMPARATIVA DE SIDE-EFFECTS Y ASINCRONÍA EN JETPACK COMPOSE (CON EJEMPLOS)
                ==================================================================================================================================================================================================================
                 * Herramienta / Concepto   | Definición Simple                  | ¿Cuándo se ejecuta?                    | ¿Para qué se usa?                   | Ejemplo Código Corto
                 *--------------------------+------------------------------------+----------------------------------------+-------------------------------------+-----------------------------------------------------
                 * LaunchedEffect           | Lanza corrutinas seguras dentro    | Al entrar a la pantalla o cuando       | Hacer peticiones a una API o        | LaunchedEffect(userId) {
                 *                          | del ciclo de vida del componente.  | cambian sus parámetros clave (llaves). | cargar datos al iniciar.            |     viewModel.cargarPerfil(userId)
                 *                          |                                    |                                        |                                     | }
                 *--------------------------+------------------------------------+----------------------------------------+-------------------------------------+-----------------------------------------------------
                 * SideEffect               | Sincroniza el estado de Compose    | Después de cada recomposición          | Enviar datos a herramientas de      | SideEffect {
                 *                          | con código externo no-Compose.     | exitosa del componente.                | analíticas o SDKs externos.         |     analytics.logEvent("Pantalla_Renderizada")
                 *                          |                                    |                                        |                                     | }
                 *--------------------------+------------------------------------+----------------------------------------+-------------------------------------+-----------------------------------------------------
                 * rememberCoroutineScope   | Te da un alcance (scope) manual    | Solo cuando lo llamas explícitamente   | Mostrar un Snackbar o hacer scroll  | Button(onClick = {
                 *                          | para lanzar corrutinas en eventos. | (por ejemplo, en un clic de botón).    | automático tras un evento.          |     scope.launch { scaffoldState.showSnackbar("!") }
                 *                          |                                    |                                        |                                     | }) { ... }
                 *--------------------------+------------------------------------+----------------------------------------+-------------------------------------+-----------------------------------------------------
                 * DisposableEffect         | Ejecuta un efecto que requiere     | Al entrar a la composición y activa    | Registrar y eliminar listeners,     | DisposableEffect(Unit) {
                 *                          | limpieza obligatoria (sin fugas).  | su bloque 'onDispose' al salir.        | receptores o sensores del sistema.  |     sensor.start()
                 *                          |                                    |                                        |                                     |     onDispose { sensor.stop() }
                 *                          |                                    |                                        |                                     | }
                 *--------------------------+------------------------------------+----------------------------------------+-------------------------------------+-----------------------------------------------------
                 * Funciones Suspendidas    | Bloques asíncronos que pueden      | No se ejecutan solas; requieren ser     | Tareas pesadas de larga duración    | suspend fun leerBaseDatos(): List<User> {
                 * (suspend fun)            | pausarse y reanudarse sin bloquear | llamadas dentro de LaunchedEffect o    | en segundo plano (red, BD, etc).    |     return database.userDao().getAll()
                 *                          | el hilo principal de la UI.        | un CoroutineScope.                     |                                     | }
                ==================================================================================================================================================================================================================
                *
                 * */

                // USO DE LaunchedEffect
                //MyPantalla() //ejemplo de un efecto secundario con la simulacion de una tarea asincrona mediante LaunchedEffect, dicha tarea se ejecuta 2 segundos despues de cargarse la pantalla
                //CounterLaunchedEffect() // ejemplo de LaunchedEffect con ejecucion constante gracias a que se establece una key o parametro diferente a Unit, cada vez que esta cambia de valor se ejecuta el LaunchedEffect

                // USO DE rememberCoroutineScope
                //RememberCoroutineScopeExample()

                // Ejemplo de funciones suspendidas y corrutinas
                //corrutineScopeEjemplo()

                // ejemplo de sideEffect -> es similar a launch effect, pero es para cualquier operación en el código que modifica un
                // estado fuera del ámbito de su función inmediata o interactúa con el exterior (como modificar variables globales,
                // guardar en base de datos, llamadas a API o imprimir por consola).

                //SideEffectEjemplo() //  sideEffect se ejecuta despues de cada recomposición y se ejecuta en el hilo principal no en segundo plano

                //CorrutinaSuspendidaConTareaLenta() //ejemplo de uso de funcion suspendida dentro de una corrutina
                //DisposableEffectEjemplo() // DisposableEffect sirve para ejecutar un efecto secundario que será limpiado o eliminado al quitar el Composable, por ejemplo al cerrar la aplicacion se ejecutará el onDispose
                //ContenedorPadre() // ejemplo de rememberUpdatedState

                //ProduceStateEjemplo() // ejemplo deProduce State
                //FrasesRandom()  // nuevo ejemplo deProduce State

                sanpshotFlowEjemplo() // ejemplo de sanpshotFlow
            }
        }
    }

}

// USO DE LaunchedEffect

@Composable
fun CounterLaunchedEffect(){
    var counter by remember { mutableIntStateOf(0) }

    // establecemos el LaunchedEffect para que se ejecute cada vez que se modifica el contador
    LaunchedEffect(counter) {
        Log.d("LaunchedEffect", "El valor del contador es: $counter")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Contador: $counter",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { counter++ }
        ) {
            Text(
                text = "Incrementar"
            )
        }
    }
}

@Composable
fun MyPantalla(){
    LaunchedEffect(Unit) {
        /**
         * todoo lo escrito aquí se ejecutará
         * una sola vez debido al parametro Unit
         */

        // simulamos una tarea asincrona
        delay(2000)
        Log.d("LaunchedEffect", "Se ejecutó después de 2 segundos") // visualizar registros en modo depuracion (.d), "LaunchedEffect" -> sirve para filtrar por ese nombre en la consola
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Hola jetpack Compose"
        )

    }
}

// USO DE rememberCoroutineScope

@Composable
fun RememberCoroutineScopeExample(){
    val coroutineScope = rememberCoroutineScope() // almacena el valor cada vez que se redibuja la pantalla

    var text by remember { mutableStateOf("Presiona el botón") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // llamando al coroutineScope
                coroutineScope.launch {
                    // aqui se ponen las corutinas o tareas que se ejecutan de forma asincrona
                    text = "Cargando" // cambiamos el contenido de text by remember
                    delay(2000) // tiempo de espera sin bloquear el HILO principal
                    text = "Descargando." // cambiamos el contenido de text by remember
                    delay(2000) // tiempo de espera sin bloquear el HILO principal
                    text = "Instalando." // cambiamos el contenido de text by remember
                    delay(2000) // tiempo de espera sin bloquear el HILO principal
                    text = "Tarea completada" // cambiamos el contenido de text by remember
                }
            }
        ) {
            Text(
                text = "Ejecutar tarea"
            )
        }

    }
}


// ejemplo con funcion suspendida y corrutinas

suspend fun MiFuncionSuspendida(){
    /*
    * Simula la ejecucion de una tarea lenta
    * Las funciones suspendidas solo se pueden
    * llamar desde otra funcion suspendida o
    * dentro de una corrutina
    * */

    Log.d("Corrutinas", "Iniciando tarea lenta") // el tag de Log.d es para buscar el mensaje en la consola
    delay(3000) // pausa la ejecucion 3000 = 3 segundos
    Log.d("Corrutinas", "Tarea lenta finalizada")

}

@Composable
fun corrutineScopeEjemplo(){

    // corrutineScope es como un contenedor de las funciones suspendidas o corrutinas
    val corrutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Al presionar el boton se ejecutará una tarea y el resultado se verá en la terminal., mediante el tag Corrutinas"
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                corrutineScope.launch {
                    //Ambito de ejecucion de las corrutinas
                    // Aquí se llaman las funciones suspendidas
                    MiFuncionSuspendida()
                }
            }
        ) {
            Text(
                text = "Iniciar tarea lenta."
            )

        }
    }
}


// side effect - permite efectos secundarios pero fuera del sistema de composicion

@Composable
fun SideEffectEjemplo(){
    /*
    * sideEffect se ejecuta despues de cada recomposición y se ejecuta en el hilo principal no en segundo plano
    *
    * Se puede usar cuando se necesite ejecutar codigo que afecte estados externos
    * al sistema de composicion como por ejemplo logs, analiticas, actualizaciones
    * en un viewModel, cuando se necesite comunicar cambios o informacion derivada del estado
    * de la UI hacia sistemas externos sin provocar recomposiciones innecesarias o
    * cuando se quiera ejecutar codigo sencillo de manera síncrona en el hilo principal sin
    * operaciones asincronas o corrutinas.
    *
    * */
    var items by rememberSaveable{ mutableStateOf(listOf("item1","item2","item3",))}

    SideEffect {
        Log.d("SideEffect","La lista tiene ${items.size} elementos.")

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de elementos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items){ item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)

                    )
                }
            }
        }
    }
}

// Ejemplo de uso de funcion suspendida desde una corrutina

suspend fun tareaLenta(){
    Log.d("Corrutina","Iniciando la tarea lenta")
    delay(3000.milliseconds) // pausa la ejecucion por poco mas de 3 seg
    Log.d("Corrutina","Finalizando la tarea lenta")
}

@Composable
fun CorrutinaSuspendidaConTareaLenta(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(Unit) {
            tareaLenta()
        }
        Text(
            text = "Ver el logcat con el tag:Corrutina para revisar el flujo de la tarea suspendida",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}



// ejemplo de disposableEffect
@Composable
fun DisposableEffectEjemplo(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisposableEffect(Unit) {
            Log.d("DisposableEffect", "Composable se ha compuesto")

            // esto se ejecuta al eliminar el composable, puede ser al cerrar la aplicacion por ejemplo
            // sirve para hacer "limpieza"
            onDispose {
                // lo que está aqui se ejecutará al cerrar o eliminar el Composable o la aplicacion
                Log.d("DisposableEffect","El Composable ha salido de composición")
            }
        }
        Text(
            text = "Este es un ejemplo de DisposableEffect Ver el logcat con el tag: DisposableEffect. ",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}


// ejemplo de rememberUpdatedState
// =========================================================================
// EJEMPLO CORREGIDO: PROPÓSITO REAL DE rememberUpdatedState
// =========================================================================
/*
 * ¿Qué es rememberUpdatedState y para qué sirve?
 * Es una función de Jetpack Compose que permite a un efecto secundario (como LaunchedEffect)
 * acceder al valor MÁS RECIENTE de un parámetro que cambia desde el exterior,
 * SIN necesidad de reiniciar o cancelar dicho efecto secundario (manteniendo la clave como Unit).
 *
 * ¿Cuándo es obligatorio?
 * Cuando el valor que necesitas dentro del efecto secundario NO es un MutableState local,
 * sino un parámetro común (String, Int, una función lambda/callback, etc.) que viene de un componente Padre.
 */

@Composable
fun ContenedorPadre() {
    // 1. El Padre maneja el estado mutable local que provocará recomposiciones.
    var mensajeParaElHijo by remember { mutableStateOf("Hola desde compose") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // 2. Al hacer clic, modificamos el String. El Padre se recompone
                // y le envía el nuevo String plano al componente Hijo.
                mensajeParaElHijo = "Nuevo mensaje"
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Cambiar mensaje por default.")
        }

        // 3. Invocamos al Hijo pasándole un String plano (NO un MutableState)
        ComponenteHijoConTimer(mensajeExterno = mensajeParaElHijo)
    }
}

@Composable
fun ComponenteHijoConTimer(mensajeExterno: String) { // <- Recibe un String plano ordinario
    val context = LocalContext.current

    // 4. AQUÍ ESTÁ LA CLAVE RECORREGIDA:
    // 'rememberUpdatedState' toma el String plano 'mensajeExterno'. Cada vez que el Padre
    // recompone al Hijo con un texto nuevo, esta referencia se actualiza internamente en tiempo real,
    // PERO no altera las llaves del LaunchedEffect.
    val currentMessage by rememberUpdatedState(newValue = mensajeExterno)

    // 5. El efecto se ejecuta UNA Sola vez al crearse el Hijo porque su clave es 'Unit'.
    LaunchedEffect(Unit) {
        // 6. El código espera de forma asíncrona durante 5 segundos.
        delay(5000)

        // 7. PRUEBA A (CORRECTA): Usamos 'currentMessage'.
        // Si el usuario presionó el botón a los 2 segundos, el Padre recompuso al Hijo.
        // Gracias a rememberUpdatedState, aquí se leerá el valor fresco: "Nuevo mensaje".
        Toast.makeText(context, currentMessage, Toast.LENGTH_LONG).show()

        // 8. PRUEBA B (INCORRECTA - Comentada para comprobar el error):
        // Si descomentas la línea de abajo y comentas la de arriba, verás que el LaunchedEffect
        // quedó "congelado" con el primer String que recibió al nacer ("Hola desde compose").
        // Aunque presiones el botón, el Toast SIEMPRE mostrará el texto viejo porque capturó una variable estática.
        // Toast.makeText(context, mensajeExterno, Toast.LENGTH_LONG).show()
    }
}


/* Ejemplos de produceState
*
* es una función de Jetpack Compose en Kotlin que se utiliza
* para convertir un flujo de datos externo (como una Corrutina,
* un Flow, un LiveData o una petición de red) en un Estado de
* Compose (State<T>) que la interfaz de usuario pueda observar
* y reaccionar automáticamente.
*
* Es, en esencia, un sustituto limpio que
* combina remember y LaunchedEffect en un
* solo bloque de código.
*
*
* ¿Cómo funciona?Cuando produceState entra en la composición,
* lanza una corrutina en segundo plano. Dentro de ese bloque,
* puedes realizar operaciones asíncronas y usar la función value
* para actualizar el estado. Cuando la actualizas, Compose redibuja
* automáticamente los elementos visuales que dependan de ese estado.
* Si produceState sale de la pantalla, la corrutina se cancela
* automáticamente de forma segura.
*
* Ventajas de usarloCódigo más limpio: No necesitas escribir un remember
* { mutableStateOf(...) } seguido de un LaunchedEffect. Todoo queda unificado.
* Seguridad de ciclo de vida: Se cancela por sí solo si el usuario sale de la pantalla,
* evitando fugas de memoria.Reactivo a cambios (Keys): Puedes pasarle llaves (key1, key2).
* Si el valor de esa llave cambia (por ejemplo, cambias de usuario), la corrutina anterior
* se destruye y arranca una nueva automáticamente.Cláusula de limpieza: Te permite usar la
* función awaitDispose { ... } al final del bloque para cerrar conexiones de WebSockets o
* listeners de bases de datos cuando el componente se destruya.
* */


@Composable
fun ProduceStateEjemplo (){

    val datos = produceState(initialValue = "Cargando...") {
        delay(5000)
        value = "Datos cargados correctamente"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = datos.value
        )

    }
}


@Composable
fun FrasesRandom(){
    val frase by produceState<String?>(initialValue = null) {
        delay(4000)
        value = listOf(
            "El conocimiento es poder",
            "nunca pares de aprender",
            "El futuro es ahora",
            "la practica hace al maestro"
        ).random()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (frase == null){
            CircularProgressIndicator()
        }else{
            Text(
                text = frase!!,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


// Ejemplo de sanpshotFlow

@Composable
fun sanpshotFlowEjemplo(){
    /*
    * snapshotFlow
    *
    * función de Jetpack Compose que convierte el sistema de estado de la interfaz en un Flow.
    * Observa los estados leídos en su interior y emite nuevos valores cada vez que estos cambian.
    *
    * Es una secuencia de datos (foto del estado) emitida de forma asincrona
    * emite un nuevo valor solo cuando el objeto que se observa
    * cambia, es ahí cuando se saca una "foto" del nuevo estado.
    *
    * Se usa normalmente dentro de LaunchedEffect o rememberCoroutineScope
    *
    * Aclaracion:
    *
    * para entender snapshot pensemos en una foto exacta de todos los valores
    * observables en el momento, si dichos valores cambian se saca una foto del estado
    * y ahí se mira si se debe o no recompones el Composable, solo si cambian los
    * valores o el valor observado, se recompone (o redibuja el elemento en pantalla)
    *
    * En este ejemplo se saca una captura o foto del estado cada vez que cambia el valor
    * del OutlinedText (campo de texto), y cada vez que se saca dicha foto verificamos si el valor
    * de una variable (showAlert) cambió, si es true mostramos un texto.
    *
    * ¿Para qué sirve?Se utiliza principalmente para ejecutar efectos secundarios cuando el estado
    * de la UI cambia. Al convertir el estado en un Flow, puedes aprovechar toda la potencia de los
    * operadores estándar como filter, map o debounce (para evitar llamadas repetidas muy seguidas).
    *
    * Un caso de uso común es el análisis de datos (analytics), por ejemplo, enviar un registro
    * cuando un usuario se desplaza más allá del primer elemento en una lista.
    *
    * */

    val textState = remember {mutableStateOf("")}
    val showAlert = remember {mutableStateOf(false)}

    // creando un efecto secundario
    LaunchedEffect(Unit){
        snapshotFlow {
            textState.value
        }.collect { text ->
            // si el usuario escribio Android o android (no importa si lo escribio en mayusculas), entonces showAlert pasa a ser true
            showAlert.value = text.contains("Android", ignoreCase = true) // retorna true o false segun lo que escriba el usuario
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // campo de ingreso de texto
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text( text = "Escribe algo...") },
            modifier = Modifier.fillMaxWidth()
        )

        // si la variable showAlert tiene true, mostramos un texto
        if (showAlert.value){
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Escribiste Android",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }



}
