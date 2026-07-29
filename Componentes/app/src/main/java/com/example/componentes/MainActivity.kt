package com.example.componentes


import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.TimePickerDialog
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.componentes.ui.theme.ComponentesTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComponentesTheme {
                /*
                * NOTAS:
                *
                * - no se puede poner un LazyColumn dentro de otro LazyColumn, esto da error,
                * esto da un conflicto matemático de tamaño infinito, lo que técnicamente se conoce
                * como un error de restricciones de altura sin límites (unbounded height constraints).
                *
                *
                    | Tip de Oro / Problema | ¿Qué pasa si lo haces mal? (El error) | ¿Cómo lo solucionas correctamente? (La regla) |
                    | :--- | :--- | :--- |
                    | **1. Cambiar estados en el flujo principal** | Provoca un bucle infinito de recomposición. La app se congela o se cierra por seguridad. | Modifica los estados únicamente dentro de eventos como `onClick`, `onValueChange` o bloques `LaunchedEffect`. |
                    | **2. Modificar estados sin usar `remember`** | La variable se reinicia a su valor inicial en cada redibujo. La interfaz se siente "congelada" o rota. | Envuelve siempre tus estados reactivos con la instrucción de memoria: `var miVariable by remember { mutableStateOf(inicial) }`. |
                    | **3. Abuso de `LocalContext.current`** | Hace que tus componentes queden acoplados. Las vistas previas (`@Preview`) de Android Studio fallarán. | Pasa el contexto o las acciones del clic hacia afuera usando funciones lambda como parámetros (ej. `onClick: () -> Unit`). |
                    | **4. Cálculos pesados en el cuerpo de la vista** | Filtrar listas grandes o procesar datos sueltos en el código ralentiza la app y genera tirones ("lag") al renderizar. | Protege el procesamiento costoso usando un bloque de memoria con clave: `val resultado = remember(lista) { lista.filtrar() }`. |
                    | **5. Carga de imágenes sin tamaño definido** | Al cargar la imagen, el contenedor cambia de tamaño de golpe. La lista da saltos bruscos y visualmente molestos. | Reserva siempre las dimensiones de tus componentes e imágenes usando modificadores fijos como `Modifier.size()` o `aspectRatio()`. |

                */

                MisComponentes() // pantalla que muestra algunos de los diferentes componentes disponibles

            }
        }
    }



}


suspend fun simulateSlowProcess(
    onStart: () -> Unit,
    onFinish: () -> Unit
){
    // simulacion de un componente que dura 3 segundos
    onStart()
    delay(3000)
    onFinish()
}

@Composable
fun MisComponentes() {

    // LazyColumn óptima para rendimiento de scroll continuo
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),

        // CORRECCIÓN: Se quita Arrangement.Center para evitar colapso de medición en scroll infinito
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /* ----- SECCIÓN: CAMPOS DE TEXTO ----- */
        // campos de texto
        item { TextFieldEjemplo() }
        item { OutlinedTextFieldEjemplo() }

        item { HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp)) }

        /* ----- SECCIÓN: SELECCIÓN SIMPLE ----- */
        // selecciones
        item { CheckBoxEjemplo() }
        item { CheckBoxEjemploAvanzado() }
        item { RadioButtonEjemplo() }
        item { SwitchEjemplo() }

        item { HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp)) }

        /* ----- SECCIÓN: MENÚS DESPLEGABLES (DROPDOWNS) ----- */

        // Cada menú debe estar aislado en su propio bloque 'item' sin scrolls internos
        // desplegables
        item { DropDownMenuEjemplo() }
        item { FormularioSeleccionEjemplo() }
        item { MenuTresPuntosEjemplo() }
        // Recuerda usar la versión corregida con el Icon nativo que evita fallas de recursos
        item { MenuAvatarPerfilEjemplo() }

        // dialogo
        item { AlertDialogEjemplo() }

        // modal
        item { ModalBottomSheetEjemplo() }

        // calendarios y hora
        item { DatePickerEjemplo() }
        item { DatePickerDialogExample() }
        item { TimePickerExample() }
        item { TimePickerDialogExample() }

        // indicadores de carga
        item { CircularProgressIndeterminadoEjemplo() }
        item { CircularProgressDeterminadoEjemplo() }
        item { LinearProgressIndicatorIndeterminadoEjemplo() }
        item { LinearProgressIndicatorDeterminadoEjemplo() }

        // sliders
        item{ SliderEjemplo() }
        item { RangeSliderEjemplo() }
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
    * Permite manejar entradas de texto tanto simples como avanzadas, ej.:
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
    * - keyboardOptions = opciones del teclado como tipo de entrada (texto, email, etc.)
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



@Composable
fun CheckBoxEjemplo() {
    // 'remember' hace que la pantalla recuerde el valor aunque se vuelva a dibujar (recomposición).
    // 'mutableStateOf' crea una variable reactiva: si cambia, la pantalla se actualiza sola.
    // 'false' significa que el checkbox inicia desmarcado.
    var accepted by remember { mutableStateOf(false) }

    // El componente visual del Checkbox (la casilla para marcar).
    Checkbox(
        // Define si la casilla se muestra marcada (true) o desmarcada (false).
        checked = accepted,
        // Evento que se dispara cuando el usuario hace clic. 'it' es el nuevo valor (true o false).
        // Guardamos ese nuevo valor en nuestra variable reactiva para actualizar la pantalla.
        onCheckedChange = { accepted = it },
        // Personalización de colores usando el tema de la aplicación.
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary, // Color de la casilla cuando está marcada.
            uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant, // Color del borde cuando está vacía.
            checkmarkColor = MaterialTheme.colorScheme.onPrimary // Color del "chulito" o palomita interna.
        )
    )

    // Agrega un espacio en blanco horizontal de 8 densidades de píxel (dp) entre el checkbox y el texto.
    Spacer(modifier = Modifier.width(8.dp))

    // Texto dinámico: usa un condicional 'if' para cambiar el mensaje según el estado de 'accepted'.
    Text(text = if (accepted) "Terminos aceptados." else "Acepta los terminos y condiciones de uso?")
}



@Composable
fun CheckBoxEjemploAvanzado() {
    // Lista de opciones a mostrar
    val options = listOf(
        "notificaciones por correo",
        "notificaciones push",
        "ofertas especiales",
        "novedades del blog"
    )

    // Recuerda el mapa para que no se reinicie en la recomposicion
    // mutableStateMapOf crea un mapa reactivo Clave Valor
    // apply inicializa todas las opciones del bucle forEach en false
    val stateOptions = remember {
        mutableStateMapOf<String, Boolean>().apply {
            options.forEach { put(it, false) }
        }
    }

    // Contenedor vertical principal que ocupa todo el ancho disponible
    Column(modifier = Modifier.fillMaxWidth()) {
        // Titulo de la seccion con estilo predefinido de Material 3
        Text(
            text = "Selecciona tus preferencias",
            style = MaterialTheme.typography.titleMedium
        )

        // Espacio vacio vertical de 8.dp
        Spacer(modifier = Modifier.height(8.dp))

        // Columna secundaria para agrupar todas las filas de checkboxes
        Column(modifier = Modifier.fillMaxWidth()) {
            // Recorre la lista de opciones de forma secuencial hacia abajo
            options.forEach { option ->
                // Organiza el cuadro de seleccion y el texto uno al lado del otro
                // CenterVertically alinea perfectamente los centros de ambos elementos
                // padding vertical de 4.dp evita que las opciones queden pegadas
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    // Componente grafico de la casilla de verificacion
                    Checkbox(
                        // Lee el estado actual en el mapa para marcar o desmarcar
                        checked = stateOptions[option] == true,
                        // Al pulsar actualiza el mapa con el nuevo valor booleano
                        onCheckedChange = { stateOptions[option] = it },
                        // Define los colores del componente usando la paleta del tema
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary, // Fondo al marcar
                            uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant, // Borde al desmarcar
                            checkmarkColor = MaterialTheme.colorScheme.onPrimary // Color del chulo interno
                        )
                    )
                    // Espacio vacio horizontal de 8.dp para separar el cuadro del texto
                    Spacer(modifier = Modifier.width(8.dp))
                    // Muestra el nombre de la opcion asignada a esta fila
                    Text(text = option)
                }
            }
        }

        // Espacio vacio de 8.dp antes de la zona de resultados
        Spacer(modifier = Modifier.height(8.dp))

        // Filtra el mapa para extraer solo los nombres de las claves que son true
        val selected = stateOptions.filter { it.value }.keys

        // Muestra en tiempo real la lista unida por comas o escribe Ninguna si esta vacia
        Text(
            text = "Los valores seleccionados son: ${if (selected.isEmpty()) "Ninguna" else selected.joinToString()}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RadioButtonEjemplo(){
    // Lista estatica con las frutas disponibles para el grupo exclusivo
    val options = listOf("Banano", "Manzana", "Pera", "Durazno", "Papaya")

    // Recuerda la opcion seleccionada guardando Banano como valor inicial
    var selectedOption by remember { mutableStateOf(options[0]) }

    // Columna para organizar de forma vertical el titulo y las frutas
    Column(modifier = Modifier.fillMaxWidth()) {
        // Mensaje explicativo para orientar al usuario con padding vertical de 8.dp
        Text(
            text = "Selecciona tu fruta favorita",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Contenedor que agrupara las filas de los RadioButtons
        Column(modifier = Modifier.fillMaxWidth()) {
            // Itera la lista de frutas para construir una opcion por vuelta
            options.forEach { option ->
                // Distribuye de forma horizontal el boton circular y el texto
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    // Componente grafico de seleccion unica circular
                    RadioButton(
                        // Se marca como activo si la opcion coincide con la seleccionada
                        selected = (option == selectedOption),
                        // Al hacer clic actualiza el estado borrando la seleccion previa
                        onClick = { selectedOption = option },
                        // Aplica los colores de la paleta oficial de Material 3
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary, // Circulo activo
                            unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant // Borde inactivo
                        )
                    )
                    // Texto con el nombre de la fruta con padding izquierdo para no tocar el boton
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Separador vertical de 12.dp antes de la confirmacion
        Spacer(modifier = Modifier.height(12.dp))

        // Imprime dinamicamente la fruta favorita actual seleccionada por el usuario
        Text(
            text = "¡$selectedOption! A mí también me gusta esa fruta.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun SwitchEjemplo(){
    // Variable reactiva para saber si el Wifi esta encendido o apagado
    var wifiEnable by remember { mutableStateOf(false) }

    // Distribuye los textos a la izquierda y el interruptor a la derecha mediante SpaceBetween
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Columna con peso de 1f para reclamar el espacio libre izquierdo antes del Switch
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Nombre de la tecnologia evaluada
            Text(
                text = "Wifi",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
            // Espacio de 4.dp de altura entre los textos de la columna
            Spacer(modifier = Modifier.height(4.dp))
            // Cambia el texto a Conectado o Desconectado segun el valor booleano
            Text(
                text = if(wifiEnable) "Conectado" else "Desconectado",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Componente deslizable de activacion Switch
        Switch(
            // Asigna el estado visual leyendo la variable booleana
            checked = wifiEnable,
            // Actualiza el estado reactivo inmediatamente al alternar el boton
            onCheckedChange = { wifiEnable = it },
            // Permite inyectar un componente personalizado dentro del circulo deslizante
            thumbContent = {
                // Evaluacion para decidir el icono interno del Switch
                if (wifiEnable){
                    // Pinta un icono nativo de confirmacion si esta encendido
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Activado",
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                } else {
                    // Pinta una equis nativa si el interruptor esta apagado
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Desactivado",
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            },
            // Aplica la configuracion nativa de colores de Material 3
            colors = SwitchDefaults.colors()
        )
    }
}


/* menu desplegable dropdownmenu*/

@Composable
fun DropDownMenuEjemplo() {
    // Controla si el menú desplegable está visible (true) u oculto (false)
    var expanded by remember { mutableStateOf(false) }

    // Almacena la opción seleccionada actualmente (un par de Texto e Icono)
    // Inicialmente es null porque no hay ninguna selección
    var selectedOption by remember { mutableStateOf<Pair<String, ImageVector>?>(null) }

    // ANIMACIÓN DE LA FLECHA:
    // Si 'expanded' es true, rota 180 grados (apunta arriba). Si es false, vuelve a 0 grados (apunta abajo).
    val angle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotación de la flecha"
    )

    // Lista de opciones estáticas que se mostrarán en el menú
    // Cada elemento es un Pair (par) que asocia un texto con su respectivo icono de Material Design
    val options = listOf(
        "Inicio" to Icons.Default.Home,
        "Favoritos" to Icons.Default.Favorite,
        "Configuración" to Icons.Default.Settings,
        "Contacto" to Icons.Default.Contacts,
        "Galeria" to Icons.Default.Image
    )

    // Contenedor vertical principal que ocupa todoo el ancho y tiene un margen superior de 100dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos hijos horizontalmente
    ) {
        // Box actúa como un contenedor de anclaje para que el DropdownMenu sepa exactamente dónde posicionarse
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Botón principal que el usuario presiona para abrir el menú o ver la opción seleccionada
            Button(
                onClick = { expanded = true } // Al hacer clic, cambia el estado a true para mostrar el menú
            ) {
                // Disposición horizontal dentro del botón para alinear el icono y el texto
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bloque condicional: si selectedOption no es null, extrae su icono (second) y lo dibuja
                    selectedOption?.second?.let { icon ->
                        Icon(
                            imageVector = icon,
                            contentDescription = selectedOption?.first, // Descripción de accesibilidad
                            modifier = Modifier.size(20.dp) // tamaño del icono
                        )
                        // Espacio horizontal de 8dp entre el icono y el texto del botón
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    // Muestra el texto de la opción seleccionada (first). Si es null, muestra el texto por defecto
                    Text(
                        selectedOption?.first ?: "Selecciona una opcion"
                    )

                    // AGREGAR LA FLECHA DE DESPLIEGUE:
                    // Dejamos un espacio a la izquierda de la flecha
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Icono nativo de flecha hacia abajo
                        contentDescription = "Indicador de menú desplegable",
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(angle) // APLICAR ROTACIÓN: Vincula el ángulo animado aquí
                    )
                }
            }

            // Componente nativo del menú desplegable
            DropdownMenu(
                expanded = expanded, // Vincula la visibilidad al estado 'expanded'
                onDismissRequest = { expanded = false }, // Se ejecuta cuando el usuario toca fuera del menú, ocultándolo
                offset = DpOffset(x = (-60).dp, y = 0.dp) // Ajusta la posición de aparición del menú en la pantalla
            ) {
                // Saca de la lista cada par mediante desestructuración (text, icon) para crear sus filas correspondientes
                options.forEach { (text, icon) ->
                    // Fila individual dentro del menú desplegable
                    DropdownMenuItem(
                        text = {
                            // Diseño interno de cada fila de opción (Icono + Espacio + Texto)
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = text,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = text)
                            }
                        },
                        onClick = {
                            // Acción al seleccionar una opción:
                            selectedOption = text to icon // 1. Actualiza la opción seleccionada en el botón principal
                            expanded = false              // 2. Cierra automáticamente el menú
                        }
                    )
                }
            }
        }
    }
}


/* Ejemplo 2 desplegable */

@Composable
fun FormularioSeleccionEjemplo() {
    // 1. ESTADO DE VISIBILIDAD: Controla si el menú está desplegado
    var expanded by remember { mutableStateOf(false) }

    // 2. ESTADO DE SELECCIÓN RESISTENTE A GIROS:
    // Usamos 'rememberSaveable' para que el texto no se borre si el usuario rota el teléfono
    var selectedOption by rememberSaveable { mutableStateOf("") }

    // 3. ANIMACIÓN DE LA FLECHA: Rota 180 grados de forma fluida al abrirse
    val arrowRotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotación Flecha Formulario"
    )

    // Listado de opciones totalmente diferente (Ejemplo: Roles de usuario)
    val rolesDeUsuario = listOf(
        "Administrador",
        "Editor",
        "Autor",
        "Colaborador",
        "Suscriptor"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenedor Box que sirve de anclaje para posicionar el DropdownMenu
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // 4. CAMPO DE TEXTO ESTILIZADO (OutlinedTextField)
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { }, // Se deja vacío porque el usuario no escribe directamente
                readOnly = true,      // Hace que el campo sea de solo lectura (evita que se abra el teclado numérico/alfabético)
                label = { Text("Selecciona tu rol de usuario") },
                placeholder = { Text("Elige una opción...") },
                trailingIcon = {
                    // Icono de flecha animado dentro del propio TextField
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Flecha indicadora",
                        modifier = Modifier
                            .rotate(arrowRotationAngle)
                            .clickable { expanded = !expanded } // Permite abrir/cerrar tocando directamente la flecha
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    // Al hacer clic en cualquier parte del campo, se despliega el menú
                    .clickable { expanded = true }
            )

            // 5. MENÚ DESPLEGABLE ASOCIADO
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                // Modificador para obligar al menú a tener el mismo ancho exacto que el OutlinedTextField
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                rolesDeUsuario.forEach { rol ->
                    DropdownMenuItem(
                        text = { Text(text = rol) },
                        onClick = {
                            selectedOption = rol  // Asigna el nuevo rol seleccionado
                            expanded = false      // Cierra el menú automáticamente
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuTresPuntosEjemplo() {
    // Estado para controlar la visibilidad del menú desplegable
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // El Box actúa como el anclaje físico. El menú se posicionará respecto a este contenedor.
        Box(contentAlignment = Alignment.Center) {

            // Un IconButton es un botón circular diseñado específicamente para contener un Icon
            IconButton(
                onClick = { expanded = true } // Al presionar el icono, abrimos el menú
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert, // Icono nativo de 3 puntos verticales
                    contentDescription = "Opciones del elemento"
                )
            }

            // El menú se despliega justo debajo del IconButton gracias al contenedor Box anterior
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cierra el menú al tocar fuera de él
            ) {
                // Opción 1: Editar
                DropdownMenuItem(
                    text = { Text("Editar artículo") },
                    leadingIcon = { // Icono al inicio de la fila
                        Icon(Icons.Default.Edit, contentDescription = null)
                    },
                    onClick = {
                        expanded = false // Recuerda cerrar el menú tras la acción
                        /* TODO: Agregar lógica para editar */
                    }
                )

                // Opción 2: Compartir
                DropdownMenuItem(
                    text = { Text("Compartir") },
                    leadingIcon = {
                        Icon(Icons.Default.Share, contentDescription = null)
                    },
                    onClick = {
                        expanded = false
                        /* TODO: Agregar lógica para compartir */
                    }
                )

                // Divider dibuja una sutil línea separadora horizontal muy común en Material 3
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                // Opción 3: Eliminar (Con colores de alerta usando la propiedad 'colors')
                DropdownMenuItem(
                    text = { Text("Eliminar") },
                    leadingIcon = {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.error,      // Texto en rojo
                        leadingIconColor = MaterialTheme.colorScheme.error // Icono en rojo
                    ),
                    onClick = {
                        expanded = false
                        /* TODO: Agregar lógica para eliminar */
                    }
                )
            }
        }
    }
}

@Composable
fun MenuAvatarPerfilEjemplo() {
    var expanded by remember { mutableStateOf(false) }

    // NOTA: Se eliminó la Column externa con márgenes altos para evitar conflictos dentro de LazyColumn
    Box(
        contentAlignment = Alignment.Center
    ) {
        // CORRECCIÓN PRINCIPAL: Se cambia Image por Icon con un vector nativo del sistema
        Icon(
            imageVector = Icons.Default.AccountCircle, // Icono estándar que no genera error de recursos al reciclarse
            contentDescription = "Foto de perfil del usuario",
            tint = MaterialTheme.colorScheme.primary,   // Color adaptado al tema de tu app
            modifier = Modifier
                .size(60.dp)                            // Dimensiones del avatar
                .clip(CircleShape)                      // Forma circular perfecta
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape) // Borde estético
                .clickable { expanded = true }          // Despliega el menú al hacer clic
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Fila informativa deshabilitada
            DropdownMenuItem(
                text = {
                    Column {
                        Text("Juan Pérez", style = MaterialTheme.typography.bodyLarge)
                        Text("juan.perez@email.com", style = MaterialTheme.typography.bodySmall)
                    }
                },
                enabled = false,
                onClick = {}
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            DropdownMenuItem(
                text = { Text("Mi Perfil") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                onClick = { expanded = false }
            )

            DropdownMenuItem(
                text = { Text("Ajustes de cuenta") },
                leadingIcon = { Icon(Icons.Default.Settings, contentDescription = null) },
                onClick = { expanded = false }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // CORRECCIÓN ICONO DEPRECADO: Se usa la variante AutoMirrored oficial de Material 3
            DropdownMenuItem(
                text = { Text("Cerrar Sesión") },
                leadingIcon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) },
                onClick = { expanded = false }
            )
        }
    }
}


/* mensaje pop de alerta alertDialog */

@Composable
fun AlertDialogEjemplo() {
    // Estado booleano para controlar si el diálogo está visible (true) u oculto (false)
    var showDialog by remember { mutableStateOf(false) }

    // Botón principal que activa el diálogo de alerta
    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error // Color rojo de alerta
        )
    ) {
        Text("Eliminar Cuenta")
    }

    // Estructura del AlertDialog de Material 3
    if (showDialog) {
        AlertDialog(
            // Se ejecuta si el usuario toca fuera del diálogo o presiona el botón "Atrás" del sistema
            onDismissRequest = { showDialog = false },

            // Icono decorativo opcional en la parte superior del diálogo
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Alerta",
                    tint = MaterialTheme.colorScheme.error
                )
            },

            // Título principal del mensaje emergente
            title = {
                Text(text = "¿Confirmar eliminación?")
            },

            // Texto con el cuerpo del mensaje explicativo
            text = {
                Text("Esta acción es permanente y perderás todos tus datos guardados. ¿Deseas continuar?")
            },

            // Botón de Confirmación (Normalmente a la derecha)
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false // Cierra el diálogo
                        /* TODO: Ejecutar la lógica de confirmación aquí */
                    }
                ) {
                    Text("Confirmar", color = MaterialTheme.colorScheme.error)
                }
            },

            // Botón de Cancelar (Normalmente a la izquierda)
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false // Cierra el diálogo sin hacer nada
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

/* modal ejemplo */
/**
 * =================================================================================
 * FUNCIÓN: ModalBottomSheetEjemplo
 * =================================================================================
 * DESCRIPCIÓN GENERAL:
 * Muestra un panel inferior (Modal Bottom Sheet) que emerge desde la base de la
 * pantalla. Se superpone al contenido principal y oscurece el fondo para centrar
 * la atención del usuario en las opciones del panel.
 *
 * ELEMENTO CENTRAL: ModalBottomSheet (Material 3)
 * Contenedor deslizable que presenta acciones o información complementaria.
 *
 * PROPIEDADES Y MÉTODOS CLAVE:
 * - sheetState: Controla visualmente el estado del modal mediante 'rememberModalBottomSheetState'.
 *   Con 'skipPartiallyExpanded = true' se despliega directamente a pantalla completa.
 * - onDismissRequest: Bloque de código ejecutado al cerrar el modal (deslizar abajo o tocar fuera).
 * - containerColor: Define el color de fondo del panel contenedor.
 * - shape: Modifica el diseño de los bordes del modal (redondeado superior por defecto).
 * - scrimColor: Color de la capa atenuante que oscurece el fondo externo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetEjemplo(){
    // Controla y recuerda si el modal está expandido o colapsado
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Evita estados intermedios y abre por completo
    )

    // Estado reactivo que activa o desactiva la visibilidad del modal
    var showSheet by remember { mutableStateOf(false) }

    // Contenedor base que centra el botón de apertura
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = { showSheet = true } // Modifica el estado a verdadero para abrir
        ) {
            Text(text = "Mostrar ModalButtonSheet")
        }
    }

    // Si el estado es verdadero, el compilador dibuja el componente en pantalla
    if (showSheet){
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }, // Esconde el modal al interactuar fuera
            sheetState = sheetState,                  // Vincula la animación y estado de posición
            containerColor = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large,
            scrimColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.32f)
        ) {
            // Contenido vertical interno del panel
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "¡Hola desde el Modal Button Sheet!")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { showSheet = false } // Botón interno para cerrar el panel
                ) {
                    Text(text = "Cerrar")
                }
            }
        }
    }
}


/**
 * =================================================================================
 * FUNCIÓN: DatePickerEjemplo
 * =================================================================================
 * DESCRIPCIÓN GENERAL:
 * Renderiza un calendario interactivo en pantalla. El usuario selecciona un día,
 * presiona un botón de confirmación y el sistema transforma la marca de tiempo (milisegundos)
 * en una fecha legible de formato dd/MM/yyyy.
 *
 * ELEMENTO CENTRAL: DatePicker (Material 3)
 * Componente nativo para la selección visual de fechas en Jetpack Compose.
 *
 * PROPIEDADES Y MÉTODOS CLAVE:
 * - state: Motor del componente gestionado por 'rememberDatePickerState'. Almacena y
 *   provee la fecha seleccionada mediante su propiedad 'selectedDateMillis'.
 * - showModeToggle: Activa un botón alternador para pasar de calendario visual a entrada por texto.
 * - title: Ranura de personalización para el título superior del componente.
 * - headline: Ranura para la etiqueta principal que describe el propósito de la selección.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerEjemplo(){
    // Obtiene la zona horaria del dispositivo del usuario
    val zoneId = ZoneId.systemDefault()

    // Obtiene la fecha actual (Año, Mes, Día) sin registrar la hora local
    val curretDate = LocalDate.now(zoneId)

    // Calcula los milisegundos desde la época Unix para fijar el día de hoy
    val startOfDayMillis = curretDate
        .atStartOfDay(zoneId) // Asocia la fecha con la hora 00:00:00 local
        .toInstant()         // Convierte el momento al estándar global UTC
        .toEpochMilli()      // Obtiene el equivalente en milisegundos totales

    // Inicializa el estado del calendario con el día de hoy seleccionado por defecto
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = startOfDayMillis)

    // Almacena el texto final formateado que se renderizará en pantalla
    var selectedDateText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Vista del calendario integrado de Material 3
        DatePicker(
            state = datePickerState,
            showModeToggle = true,          // Habilita la edición manual por teclado
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Titulo del DatePicker",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            headline = {
                Text(
                    text = "Seleccione la fecha de nacimiento",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Recupera la marca de tiempo en milisegundos elegida
                val selectedMillis = datePickerState.selectedDateMillis

                if (selectedMillis != null){
                    // Transforma milisegundos a días totales dividiendo por 86,400,000 (milisegundos/día)
                    val daysSinceEpoch = selectedMillis / (24 * 60 * 60 * 1000)

                    // Instancia un LocalDate usando el contador de días Unix
                    val localDate = LocalDate.ofEpochDay(daysSinceEpoch)

                    // Modifica la apariencia del string resultante a dd/MM/yyyy
                    val dateString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                    // Actualiza la pantalla asignando el valor a la variable de estado
                    selectedDateText = dateString
                }
            }
        ) {
            Text(text = "Confirmar fecha seleccionada")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el texto del resultado en la UI únicamente si ya fue calculado
        if(selectedDateText.isNotEmpty()){
            Text(
                text = "Fecha seleccionada: $selectedDateText",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


/**
 * Ejemplo de uso de un DatePickerDialog en Jetpack Compose (Material 3).
 *
 * Este ejemplo demuestra cómo mostrar un diálogo de selección de fecha (Calendario),
 * capturar la selección del usuario en milisegundos, convertirla a un formato local
 * y mostrarla en la pantalla principal.
 *
 * Propiedades y comportamiento del Composable principal (DatePickerDialogExample):
 * - Estado del Calendario (datePickerState): Inicializado por defecto con la fecha actual del sistema.
 * - Control de Visibilidad (showDialog): Variable booleana reactiva que determina si el diálogo está abierto o cerrado.
 * - Persistencia del Texto (selectedDateText): Almacena la cadena de texto con la fecha formateada ("dd/MM/yyyy").
 * - Interfaz de Usuario (UI): Centra vertical y horizontalmente un botón de apertura y una etiqueta de texto indicativa.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogExample() {
    // Obtiene la zona horaria actual configurada en el dispositivo del usuario
    val zoneId = ZoneId.systemDefault()

    // Obtiene la fecha actual (Año, Mes, Día) sin registrar la hora local
    val curretDate = LocalDate.now(zoneId)

    // Calcula los milisegundos desde la época Unix para fijar el día de hoy como predeterminado
    val startOfDayMillis = curretDate
        .atStartOfDay(zoneId) // Asocia la fecha con la hora 00:00:00 local de esa zona horaria
        .toInstant()         // Convierte el momento al estándar global de tiempo UTC
        .toEpochMilli()       // Obtiene el equivalente en milisegundos totales desde 1970

    // Inicializa y recuerda el estado del calendario con el día de hoy seleccionado por defecto
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = startOfDayMillis)

    // Almacena y recuerda el texto final formateado que se renderizará en pantalla
    var selectedDateText by remember { mutableStateOf("") }

    // Controla de forma reactiva si el cuadro de diálogo de selección debe dibujarse en pantalla
    var showDialog by remember { mutableStateOf(false) }

    // Contenedor principal que organiza los elementos visuales en una columna vertical
    Column(
        modifier = Modifier
            .fillMaxSize()     // Expande la columna para ocupar todo el ancho y alto disponible
            .padding(8.dp),    // Aplica un margen interno perimetral de 8 dp
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente en la pantalla
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Botón que desencadena la apertura del diálogo de selección de fecha
        Button(
            onClick = { showDialog = true } // Cambia el estado a verdadero para mostrar el diálogo
        ) {
            Text(text = "Abrir calendario")
        }

        // Inserta un espacio de separación vertical de 16 dp entre el botón y el texto
        Spacer(modifier = Modifier.height(16.dp))

        // Renderiza el componente de texto únicamente si el usuario ya ha seleccionado una fecha válida
        if (selectedDateText.isNotEmpty()) {
            Text(
                text = "Fecha seleccionada: $selectedDateText",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    // Condicional reactivo: Si es verdadero, levanta y dibuja el diálogo sobre la UI actual
    if (showDialog) {
        DatePickerDialog(
            // Define la acción cuando el usuario toca fuera del diálogo o presiona "Atrás"
            onDismissRequest = { showDialog = false },

            // Configura el botón de acción afirmativa ("Aceptar")
            confirmButton = {
                TextButton(
                    onClick = {
                        // Extrae la fecha seleccionada por el usuario en milisegundos (puede ser nula)
                        val selectedMillis = datePickerState.selectedDateMillis
                        if (selectedMillis != null) {
                            // Convierte los milisegundos totales a días transcurridos desde la época Unix
                            val daysSinceEpoch = selectedMillis / (24 * 60 * 60 * 1000)

                            // Convierte esos días a un objeto de fecha local manejable
                            val localDate = LocalDate.ofEpochDay(daysSinceEpoch)

                            // Aplica la máscara de formato estándar día/mes/año al objeto de fecha
                            val dateString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                            // Actualiza la variable de estado para refrescar el texto en la UI principal
                            selectedDateText = dateString
                        }
                        // Cierra el cuadro de diálogo tras procesar la selección
                        showDialog = false
                    }
                ) {
                    Text(text = "Aceptar")
                }
            },

            // Configura el botón de acción negativa o cancelación
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false } // Cierra el diálogo sin guardar cambios
                ) {
                    Text(text = "Cancelar")
                }
            }
        ) {
            // Componente interno del diálogo que contiene la lógica gráfica del calendario
            DatePicker(
                state = datePickerState, // Enlaza el estado que controla la selección del usuario
                showModeToggle = true,   // Permite alternar entre la vista de calendario y entrada de texto manual
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                title = {
                    Text(text = "Título de datepicker", style = MaterialTheme.typography.titleLarge)
                },
                headline = {
                    Text(text = "Seleccione una fecha", style = MaterialTheme.typography.bodyLarge)
                }
            )
        }
    }
}

/**
 * Ejemplo de uso de un TimePicker incrustado en Jetpack Compose (Material 3).
 *
 * Este ejemplo demuestra cómo integrar un selector de hora visual directo en la interfaz,
 * personalizar sus esquemas de color decorativos y procesar manualmente la hora capturada
 * para convertirla desde un formato militar de 24 horas a un formato civil de 12 horas (AM/PM).
 *
 * Propiedades y comportamiento del Composable principal (TimePickerExample):
 * - Estado del Selector (timePickerState): Inicializa la hora en las 10:50 y define el formato visual inicial en 12 horas.
 * - Persistencia del Texto (selectedTimeText): Almacena y actualiza el texto de la hora formateada resultante.
 * - Interfaz de Usuario (UI): Dispone de forma vertical un indicador de texto, el reloj interactivo y un botón de confirmación.
 * - Estilización Avanzada (colors): Define de manera explícita la paleta de colores para selectores, esferas y textos del reloj.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerExample() {
    // Inicializa y recuerda el estado del selector con hora predeterminada (10:50 AM)
    // is24Hour = false indica que la interfaz gráfica trabajará con interruptores AM y PM
    val timePickerState = rememberTimePickerState(
        initialHour = 10,
        initialMinute = 50,
        is24Hour = false
    )

    // Almacena y recuerda el texto final de la hora procesada que se mostrará al usuario
    var selectedTimeText by remember { mutableStateOf("") }

    // Contenedor principal que organiza verticalmente los elementos del selector
    Column(
        modifier = Modifier
            .fillMaxSize()       // Expande la columna para ocupar todo el espacio disponible
            .padding(24.dp),     // Aplica un margen interno perimetral de 24 dp
        verticalArrangement = Arrangement.Center, // Centra los componentes verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra los componentes horizontalmente
    ) {
        // Muestra condicionalmente la hora seleccionada o un mensaje instructivo inicial
        Text(
            text = if (selectedTimeText.isNotEmpty()) "Hora seleccionada: $selectedTimeText" else "Seleccione una hora"
        )

        // Agrega una separación vertical de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Componente visual del reloj de Material 3 para seleccionar horas y minutos
        TimePicker(
            state = timePickerState, // Enlaza el estado reactivo del selector
            layoutType = TimePickerLayoutType.Vertical, // Apila los elementos del reloj de forma vertical
            colors = TimePickerDefaults.colors(
                clockDialColor = MaterialTheme.colorScheme.secondaryContainer, // Color de fondo de la esfera circular del reloj
                clockDialSelectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer, // Color del número de la hora/minuto seleccionado
                clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Color atenuado para números no seleccionados
                selectorColor = MaterialTheme.colorScheme.primary, // Color de la aguja/indicador que marca la selección actual
                periodSelectorBorderColor = MaterialTheme.colorScheme.primary, // Color del borde de las casillas de selección AM o PM
                periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer, // Fondo de la casilla AM o PM que está activa
                periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant, // Fondo de la casilla AM o PM inactiva
                periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer, // Color del texto (letra) AM o PM seleccionado
                periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface // Color del texto (letra) AM o PM no seleccionado
            )
        )

        // Agrega una separación vertical de 16 dp antes del botón de confirmación
        Spacer(modifier = Modifier.height(16.dp))

        // Botón encargado de leer los datos del estado y darles el formato deseado
        Button(
            onClick = {
                // Obtiene la hora seleccionada (siempre regresa en formato militar de 24 horas, de 0 a 23)
                val hour24 = timePickerState.hour

                // Obtiene los minutos seleccionados actualmente en el estado (de 0 a 59)
                val minute = timePickerState.minute

                // Determina de forma lógica si corresponde al periodo matutino (AM) o vespertino (PM)
                val amPM = if (hour24 < 12) "AM" else "PM"

                // Conversión matemática de hora militar (24h) a hora estándar civil (12h)
                val hour12 = when {
                    hour24 == 0 -> 12      // Si es medianoche (0 horas), se traduce como las 12 AM
                    hour24 > 12 -> hour24 - 12 // Si es mayor a 12 (ej. 13h), resta 12 para obtener el formato civil (1 PM)
                    else -> hour24         // Si está en el rango de 1 a 12, el número se conserva igual
                }

                // Construye una cadena formateada con dos dígitos estructurados ("00:00 AM/PM")
                // Usa Locale.getDefault() para respetar la configuración regional del sistema operativo
                selectedTimeText = String.format(Locale.getDefault(), "%02d:%02d %s", hour12, minute, amPM)
            }
        ) {
            Text(text = "Confirmar hora")
        }
    }
}

/**
 * Ejemplo de uso de un TimePickerDialog personalizado en Jetpack Compose (Material 3).
 *
 * Este ejemplo demuestra cómo envolver un componente TimePicker dentro de un cuadro de diálogo
 * flotante (emergente), controlando su visibilidad mediante estados reactivos y procesando la hora
 * seleccionada para transformarla de formato militar (24h) a formato civil de 12 horas (AM/PM)
 * al presionar el botón de confirmación.
 *
 * Propiedades y comportamiento del Composable principal (TimePickerDialogExample):
 * - Estado del Selector (timePickerState): Define la hora inicial predeterminada (10:50) y configura la UI del reloj para trabajar con AM/PM.
 * - Control de Visibilidad (showDialog): Variable booleana reactiva que determina si el diálogo flotante se dibuja o se destruye en pantalla.
 * - Persistencia del Texto (selectedTimeText): Almacena la cadena de caracteres con la hora ya procesada y formateada para el usuario.
 * - Interfaz de Usuario (UI): Muestra una columna centrada con una etiqueta informativa y el botón que levanta el diálogo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogExample() {
    // Inicializa y recuerda el estado del selector con una hora inicial por defecto (10:50 AM)
    // is24Hour = false configura la interfaz visual para mostrar los selectores de AM y PM
    val timePickerState = rememberTimePickerState(
        initialHour = 10,
        initialMinute = 50,
        is24Hour = false
    )

    // Controla de forma reactiva si el cuadro de diálogo flotante debe mostrarse en pantalla
    var showDialog by remember { mutableStateOf(false) }

    // Almacena y recuerda el texto final formateado de la hora que se renderizará en la UI principal
    var selectedTimeText by remember { mutableStateOf("") }

    // Contenedor principal que organiza verticalmente los elementos base de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()       // Expande la columna para ocupar todo el ancho y alto de la pantalla
            .padding(24.dp),     // Aplica un margen interno perimetral de 24 dp
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Muestra dinámicamente la hora seleccionada o un mensaje instructivo si aún no hay selección
        Text(
            text = if (selectedTimeText.isNotEmpty()) "Hora seleccionada: $selectedTimeText" else "Seleccione una hora"
        )

        // Agrega una separación de 16 dp entre la etiqueta de texto y el botón
        Spacer(modifier = Modifier.height(16.dp))

        // Botón encargado de activar la visibilidad del cuadro de diálogo emergente
        Button(
            onClick = { showDialog = true } // Cambia el estado a verdadero para forzar la recomposición y mostrar el diálogo
        ) {
            Text(text = "Abrir TimePickerDialog")
        }

        // Condicional reactivo: Si es verdadero, levanta y superpone el diálogo sobre la interfaz actual
        if (showDialog) {
            // Nota: TimePickerDialog como tal no existe de forma nativa directa en M3 estándar de la misma manera que DatePickerDialog,
            // por lo que este componente suele mapearse a una implementación personalizada o un AlertDialog que envuelve al TimePicker.
            TimePickerDialog(
                // Define la acción cuando el usuario descarta el diálogo tocando fuera de él o presionando el botón "Atrás"
                onDismissRequest = { showDialog = false },

                // Configura el encabezado o etiqueta de título en la parte superior del diálogo
                title = { Text(text = "Seleccionar una hora") },

                // Configura el botón de acción positiva para confirmar y procesar los datos del reloj
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Extrae la hora del estado (siempre se entrega en formato de 24 horas, de 0 a 23)
                            val hour24 = timePickerState.hour

                            // Extrae los minutos seleccionados actualmente en el estado (de 0 a 59)
                            val minute = timePickerState.minute

                            // Evalúa lógicamente si la hora pertenece al bloque de la mañana (AM) o de la tarde/noche (PM)
                            val amPM = if (hour24 < 12) "AM" else "PM"

                            // Conversión algorítmica de formato militar (24h) a formato estándar de 12 horas
                            val hour12 = when {
                                hour24 == 0 -> 12          // Traduce la medianoche (0 horas) como las 12 AM
                                hour24 > 12 -> hour24 - 12 // Resta 12 a las horas vespertinas (ej. 15h pasa a ser 3 PM)
                                else -> hour24             // Mantiene el valor igual si se encuentra en el rango de 1 a 12
                            }

                            // Da formato a las variables como una cadena estructurada con ceros a la izquierda ("00:00 AM/PM")
                            // Usa Locale.getDefault() para heredar el idioma y región configurados en el dispositivo móvil
                            selectedTimeText = String.format(Locale.getDefault(), "%02d:%02d %s", hour12, minute, amPM)

                            // Cierra el cuadro de diálogo modificando el estado que controla su visibilidad
                            showDialog = false
                        }
                    ) {
                        Text(text = "Confirmar hora")
                    }
                },

                // Configura el botón de acción negativa para cancelar y cerrar el componente
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false } // Cierra el diálogo de inmediato sin guardar ni procesar cambios
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            ) {
                // Bloque de contenido interno: Aloja la interfaz interactiva del reloj con su respectivo estilo visual
                TimePicker(
                    state = timePickerState, // Vincula el estado que rastrea la posición de las manecillas
                    layoutType = TimePickerLayoutType.Vertical, // Dispone las horas, minutos y selectores AM/PM en formato vertical
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.secondaryContainer, // Fondo de la esfera del reloj circular
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer, // Color numérico del elemento seleccionado
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), // Color atenuado para números inactivos
                        selectorColor = MaterialTheme.colorScheme.primary, // Color de la aguja indicadora de selección
                        periodSelectorBorderColor = MaterialTheme.colorScheme.primary, // Color del borde del contenedor AM / PM
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer, // Fondo del interruptor AM o PM cuando está activo
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant, // Fondo del interruptor AM o PM cuando está inactivo
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer, // Color del texto de la opción activa (AM/PM)
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface // Color del texto de la opción inactiva (AM/PM)
                    )
                )
            }
        }
    }
}


// ejemplos de componentes de carga

/**
 * COMPONENTE PRINCIPAL: CircularProgressIndicator (Versión Indeterminada)
 *
 * ¿Qué es?: Un indicador de progreso circular y animado de Material Design.
 * ¿Para qué sirve?: Notifica al usuario que hay una operación en curso cuya duración es desconocida.
 * Parámetros clave usados: 'modifier' para el tamaño, 'strokeWidth' para el grosor y 'color' para el diseño.
 */
@Composable
fun CircularProgressIndeterminadoEjemplo() {

    // ESTADOS DE LA INTERFAZ
    // Controla si el indicador de carga circular debe mostrarse en pantalla.
    var isLoading by remember { mutableStateOf(false) }

    // Dispara el efecto secundario (LaunchedEffect) para iniciar la simulación cuando pasa a 'true'.
    var startOperation by remember { mutableStateOf(false) }

    // GESTIÓN DE EFECTOS SECUNDARIOS
    // Escucha cambios en 'startOperation'. Si cambia a true, ejecuta la corrutina.
    LaunchedEffect(startOperation) {
        if (startOperation) {
            // Llama a una función suspendida que simula una tarea pesada en segundo plano.
            simulateSlowProcess(
                onStart = {
                    isLoading = true // Activa la animación de carga antes de empezar.
                },
                onFinish = {
                    isLoading = false       // Apaga la animación al terminar.
                    startOperation = false   // Reinicia el disparador para permitir un nuevo clic.
                }
            )
        }
    }

    // DISEÑO DE LA INTERFAZ DE USUARIO (UI)
    // Contenedor vertical que centra todos sus elementos en la pantalla.
    Column(
        modifier = Modifier
            .fillMaxSize()       // Ocupa todo el ancho y alto disponible de la pantalla.
            .padding(24.dp),     // Añade un margen interno de seguridad.
        verticalArrangement = Arrangement.Center,       // Centra el contenido verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente.
    ) {
        // Título del ejemplo
        Text(
            text = "Circular progress indeterminado",
            style = MaterialTheme.typography.bodyLarge
        )

        // Espaciador vertical estático
        Spacer(modifier = Modifier.height(16.dp))

        // FLUJO CONDICIONAL SEGÚN EL ESTADO DE LA APLICACIÓN
        if (isLoading) {
            // ESTADO 1: CARGANDO - Se muestra el componente solicitado
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp), // Define un tamaño de 64x64 dp para el círculo.
                strokeWidth = 6.dp,              // Define un borde grueso y visible de 6 dp.
                color = MaterialTheme.colorScheme.primary // Aplica el color principal del tema.
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Procesando operación",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            // ESTADO 2: OPERACIÓN COMPLETADA (O ESTADO INICIAL)
            // Muestra un ícono de confirmación verde/azul (color primario)
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Operación completada",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Operación completada",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // DISPARADOR DE ACCIÓN
        // Botón que cambia el estado para iniciar todo el ciclo de carga.
        Button(
            onClick = { startOperation = true } // Cambia el estado a true al hacer clic.
        ) {
            Text(text = "Iniciar operación")
        }
    }
}

/**
 * COMPONENTE PRINCIPAL: CircularProgressIndicator (Versión Determinada)
 *
 * ¿Qué es?: Un indicador de progreso circular controlado por un valor numérico.
 * ¿Para qué sirve?: Muestra de manera visual y exacta el avance de una tarea (0% a 100%).
 *
 * PARÁMETROS ADICIONALES DEL COMPONENTE (No presentes en este ejemplo):
 * - trackColor: Color del carril/fondo del círculo sobre el cual se mueve el progreso.
 * - strokeCap: Estilo de los extremos de la línea. Se puede usar 'StrokeCap.Round' para bordes redondeados.
 */
@Composable
fun CircularProgressDeterminadoEjemplo() {

    // VALORES DE ESTADO
    // Guarda el progreso actual en un rango de 0.0f a 1.0f (inicializa en 0%)
    var progress by remember { mutableStateOf(0f) }

    // Disparador de estado para iniciar el bloque asíncrono de descarga
    var startDownload by remember { mutableStateOf(false) }

    // GESTIÓN DE CORRUTINAS (Efecto Secundario)
    // Se ejecuta de forma segura en segundo plano cuando 'startDownload' cambia a true
    LaunchedEffect(startDownload) {
        if (startDownload) {
            // Repite el bloque interno exactamente 20 veces para segmentar la carga
            repeat(20) {
                delay(150) // Pausa la corrutina por 150 milisegundos simulando latencia de red
                progress += 0.05f // Incrementa el progreso en un 5% (0.05) en cada iteración
            }
            /* Cálculo de tiempo total: 20 iteraciones * 150ms = 3000ms (3 segundos) */

            progress = 1f // Forzamos el valor a 1.0f (100%) al finalizar para mitigar errores de precisión decimal
            startDownload = false // Apaga el disparador para indicar que el proceso terminó
        }
    }

    // DISEÑO DE LA INTERFAZ DE USUARIO (UI)
    Column(
        modifier = Modifier
            .fillMaxSize()       // Ocupa la pantalla completa
            .padding(24.dp),     // Margen de separación con los bordes de la pantalla
        verticalArrangement = Arrangement.Center,       // Alineación vertical al centro
        horizontalAlignment = Alignment.CenterHorizontally // Alineación horizontal al centro
    ) {
        // Texto informativo de la acción actual
        Text(
            text = "Circular progress determinado - simulación de descarga de archivo",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // INDICADOR DE PROGRESO DETERMINADO
        CircularProgressIndicator(
            progress = { progress }, // Entrega el estado flotante actual a través de una expresión lambda
            modifier = Modifier.size(80.dp), // Ajusta las dimensiones del círculo a 80x80 dp
            strokeWidth = 10.dp, // Asigna un grosor robusto de 10 dp a la línea

            // EVALUACIÓN DINÁMICA DE COLOR
            // Si el progreso es menor al 100% usa el color Primary, si ya terminó cambia a Secondary
            color = if (progress < 1f)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TEXTO PORCENTUAL
        // Multiplica el valor flotante por 100 y lo convierte a un entero para mostrar "X%"
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÓN ACCIONADOR DE LA DESCARGA
        Button(
            onClick = { startDownload = true },
            // Se bloquea el botón si la descarga ya terminó (100%) o si actualmente está descargando
            enabled = progress < 1f && !startDownload
        ) {
            // El texto del botón cambia según el avance de la simulación
            Text(
                if (progress < 1f) "Iniciar descarga" else "Descarga completa"
            )
        }

        // CONTROL DE REINICIO
        // Este bloque condicional dibuja un botón extra únicamente cuando la descarga alcanzó el 100%
        if (progress >= 1f) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    progress = 0f          // Restablece el porcentaje a 0%
                    startDownload = false  // Asegura que el hilo secundario esté apagado
                }
            ) {
                Text("Reiniciar")
            }
        }
    }
}
/**
 * COMPONENTE PRINCIPAL: LinearProgressIndicator (Versión Indeterminada)
 *
 * ¿Qué es?: Una barra de progreso horizontal con una animación cíclica continua.
 * ¿Para qué sirve?: Indica al usuario que se está procesando una tarea en segundo plano
 *                  sin especificar una estimación exacta de tiempo o porcentaje.
 *
 * PARÁMETROS ADICIONALES EXPLICADOS:
 * - trackColor: Define el color de la barra base (fondo) para que la animación resalte.
 * - strokeCap: Modifica las esquinas de la barra; 'StrokeCap.Round' le da un aspecto moderno y suave.
 */
@Composable
fun LinearProgressIndicatorIndeterminadoEjemplo() {

    // GESTIÓN DE ESTADOS DE LA INTERFAZ
    // Controla si la barra de progreso y el texto de carga deben renderizarse en pantalla.
    var isLoading by remember { mutableStateOf(false) }

    // Registra si la operación se completó exitosamente para mostrar el mensaje de éxito.
    var isCompleted by remember { mutableStateOf(false) }

    // GESTIÓN DE EFECTOS SECUNDARIOS (Hilos de fondo)
    // Se activa de forma segura inmediatamente cuando 'isLoading' cambia a 'true'
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(5000)        // Pausa la ejecución durante 5000 milisegundos (5 segundos) simulando la tarea
            isLoading = false  // Apaga la barra de progreso horizontal
            isCompleted = true // Activa la visualización del estado finalizado
        }
    }

    // ARQUITECTURA DE LA INTERFAZ DE USUARIO (UI)
    Column(
        modifier = Modifier
            .fillMaxSize()       // Extiende el contenedor a todo lo ancho y alto del dispositivo
            .padding(24.dp),     // Aplica un margen interno perimetral
        verticalArrangement = Arrangement.Center,       // Centra el contenido en el eje vertical
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido en el eje horizontal
    ) {
        // Título descriptivo de la pantalla
        Text(
            text = "Linear progress indicator - indeterminado",
            style = MaterialTheme.typography.bodyLarge
        )

        // FLUJO CONDICIONAL SEGÚN EL ESTADO DE CARGA
        if (isLoading) {
            // CASO A: LA OPERACIÓN ESTÁ ACTIVA
            Text(
                text = "Procesando datos",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // INDICADOR HORIZONTAL SOLICITADO
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth() // Hace que la barra se expanda horizontalmente todo lo posible
                    .height(8.dp),  // Define un grosor personalizado de 8 dp para que sea muy visible
                color = MaterialTheme.colorScheme.primary, // Color de la animación móvil (Color Primario)
                trackColor = MaterialTheme.colorScheme.surfaceVariant, // Color de la barra base de fondo
                strokeCap = StrokeCap.Round // Redondea estéticamente los extremos de la barra
            )

        } else if (isCompleted) {
            // CASO B: LA OPERACIÓN HA TERMINADO CON ÉXITO
            Text(
                text = "Operación finalizada",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // BOTÓN DISPARADOR DE LA OPERACIÓN
        Button(
            onClick = {
                isLoading = true      // Inicia el proceso y arranca la corrutina
                isCompleted = false   // Oculta cualquier mensaje previo de éxito
            },
            // Deshabilita el botón mientras la carga está activa para evitar múltiples clicks accidentales
            enabled = !isLoading
        ) {
            Text(text = "Iniciar operación")
        }
    }
}
/**
 * COMPONENTE PRINCIPAL: LinearProgressIndicator (Versión Determinada)
 *
 * ¿Qué es?: Una barra de progreso horizontal cuyo llenado depende de un valor numérico continuo.
 * ¿Para qué sirve?: Informa de forma exacta y lineal el porcentaje completado de una tarea activa (0% a 100%).
 *
 * PARÁMETROS ADICIONALES EXPLICADOS (No presentes en este ejemplo):
 * - strokeCap: Permite redondear las esquinas de la barra usando 'StrokeCap.Round'.
 * - gapSize: Controla el espacio de separación entre la barra rellena y el carril de fondo.
 */
@Composable
fun LinearProgressIndicatorDeterminadoEjemplo() {

    // CONTROL DE ESTADOS DE LA INTERFAZ
    // Guarda el progreso numérico de la barra (Rango flotante de 0f a 1f)
    var progress by remember { mutableStateOf(0f) }

    // Bandera lógica para saber si la corrutina de descarga se está ejecutando actualmente
    var isDownloading by remember { mutableStateOf(false) }

    // Bandera lógica para verificar si el archivo ya se descargó por completo
    var downloadCompleted by remember { mutableStateOf(false) }

    // GESTIÓN DE EFECTOS SECUNDARIOS (Corrutinas de fondo)
    // Escucha activamente el estado de 'isDownloading'. Si cambia a 'true', arranca el bloque.
    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            downloadCompleted = false // Resetea el estado de éxito al iniciar una nueva descarga

            // Bucle que incrementa el progreso de forma controlada mientras no llegue al 100% (1f)
            while (progress < 1f) {
                delay(200)          // Pausa la ejecución por 200 milisegundos en cada iteración
                progress += 0.05f    // Incrementa un 5% el valor de progreso
            }

            isDownloading = false     // Apaga la bandera de descarga activa al salir del bucle
            downloadCompleted = true  // Activa el estado de descarga finalizada con éxito
        }
    }

    // ARQUITECTURA Y DISEÑO DE LA PANTALLA (UI)
    Column(
        modifier = Modifier
            .fillMaxSize()       // Extiende el contenedor por toda la pantalla disponible
            .padding(24.dp),     // Margen de seguridad con los bordes del dispositivo
        verticalArrangement = Arrangement.Center,       // Centra todos los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra todos los elementos horizontalmente
    ) {
        // Título descriptivo del flujo de trabajo
        Text(
            text = "Linear progress determinado - simulación de descarga de archivo",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FLUJO CONDICIONAL: MUESTRA LA BARRA SOLO MIENTRAS SE DESCARGA
        if (isDownloading) {
            // INDICADOR HORIZONTAL DETERMINADO SOLICITADO
            LinearProgressIndicator(
                progress = { progress }, // Pasa el valor del estado flotante mediante una lambda
                modifier = Modifier
                    .fillMaxWidth()     // Estira la barra para ocupar todo el ancho del contenedor
                    .height(8.dp),      // Le asigna un grosor físico visible de 8 dp
                color = MaterialTheme.colorScheme.primary, // Color de la barra que avanza (Color Primario)
                trackColor = MaterialTheme.colorScheme.surfaceVariant // Color de la barra base de fondo
            )

            Spacer(modifier = Modifier.height(16.dp))

            // VISUALIZADOR DE PORCENTAJE EN TEXTO
            // Transforma el float (0.0 a 1.0) en entero (0 a 100) añadiendo el símbolo "%"
            Text(
                text = "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // CONTROL ACCIONADOR
        // Botón encargado de disparar la acción y reiniciar los valores
        Button(
            onClick = {
                if (!isDownloading) {
                    progress = 0f         // Reinicia el progreso a cero antes de comenzar
                    isDownloading = true  // Dispara el LaunchedEffect
                }
            },
            // Se deshabilita automáticamente mientras la descarga esté en curso
            enabled = !isDownloading
        ) {
            Text(text = "Iniciar descarga")
        }

        // SECCIÓN INFORMATIVA DE ÉXITO FINAL
        // Aparece dinámicamente en pantalla cuando la bandera 'downloadCompleted' es verdadera
        if (downloadCompleted) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Archivo descargado.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary // Resalta el texto final con el color del tema
            )
        }
    }
}

// ejemplos slider

@Composable
fun SliderEjemplo(){

    var sliderValue by remember { mutableFloatStateOf(50f) }

    Column(
        modifier = Modifier
            .fillMaxSize()       // Extiende el contenedor por toda la pantalla disponible
            .padding(24.dp),     // Margen de seguridad con los bordes del dispositivo
        verticalArrangement = Arrangement.Center,       // Centra todos los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra todos los elementos horizontalmente
    ) {
        // Título descriptivo del flujo de trabajo
        Text(
            text = "Slider ejemplo\nVolumen actual: ${sliderValue.toInt()}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f,
            //steps = 3, // cantidad de divisiones internas de la barra slider
            onValueChangeFinished = {
                println( "El usuario soltó el slider en ${sliderValue}")
            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                thumbColor = MaterialTheme.colorScheme.primary
            )
        )
    }

}


@Composable
fun RangeSliderEjemplo(){
    var priceRange by remember { mutableStateOf(50f..300f) }

    Column(
        modifier = Modifier
            .fillMaxSize()       // Extiende el contenedor por toda la pantalla disponible
            .padding(24.dp),     // Margen de seguridad con los bordes del dispositivo
        verticalArrangement = Arrangement.Center,       // Centra todos los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra todos los elementos horizontalmente
    ) {
        // Título descriptivo del flujo de trabajo
        Text(
            text = "RangeSlider ejemplo\nFiltro por precio}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${priceRange.start.toInt()} USD - ${priceRange.endInclusive.toInt()} USD",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        RangeSlider(
            value = priceRange,
            onValueChange = { priceRange = it},
            valueRange = 0f..500f,
            steps = 9,
            onValueChangeFinished = {
                println("precio seleccionado: ${priceRange.start} - ${priceRange.endInclusive}")
            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                thumbColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "precio seleccionado: ${priceRange.start.toInt()} USD - ${priceRange.endInclusive.toInt()} USD",
            style = MaterialTheme.typography.bodyLarge
        )
    }

}