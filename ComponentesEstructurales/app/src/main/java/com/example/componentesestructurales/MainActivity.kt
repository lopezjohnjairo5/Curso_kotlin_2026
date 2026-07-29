// Define el paquete del proyecto para organizar tus archivos de código
package com.example.componentesestructurales

// Bloque de importaciones: Trae las herramientas necesarias de Android y Jetpack Compose
import android.R.attr.navigationIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// Importa el tema visual generado automáticamente para tu aplicación
import com.example.componentesestructurales.ui.theme.ComponentesEstructuralesTheme

// MainActivity es la pantalla o ventana principal donde inicia tu aplicación de Android
class MainActivity : ComponentActivity() {

    // onCreate es el primer método que se ejecuta cuando el sistema operativo crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura la pantalla para que el contenido ocupe todo el espacio físico (pantalla completa sin bordes)
        enableEdgeToEdge()

        // setContent define que la interfaz de usuario se construirá usando bloques de Jetpack Compose
        setContent {
            // Aplica el tema de diseño (colores, tipografías) configurado para tu proyecto
            ComponentesEstructuralesTheme {
                // Llama a la función que dibuja tu pantalla estructurada
                ScaffoldScreen()
            }
        }
    }


    /**
     * @Composable: Indica que esta función genera un elemento de la interfaz gráfica.
     *
     * ¿Qué es Scaffold?
     * Es un componente de alto nivel de Material Design que sirve como el "esqueleto" o
     * estructura base para construir pantallas completas y consistentes en Android.
     *
     * ¿Para qué sirve?
     * Su función principal es organizar y orquestar de forma automática el espacio de los
     * componentes comunes de una app, evitando que se superpongan entre sí.
     *
     * ¿Cuándo usarlo?
     * Se debe usar como el contenedor raíz de casi cualquier pantalla principal que requiera
     * elementos de navegación, barras de herramientas o interacciones globales.
     *
     * Propiedades principales (Parámetros que acepta):
     * - topBar: Para añadir la barra superior de la aplicación (TopAppBar).
     * - bottomBar: Para añadir barras de navegación inferior o de acciones (BottomAppBar).
     * - floatingActionButton: Para colocar el botón flotante de acción principal (FAB).
     * - snackbarHost: Para gestionar y mostrar notificaciones flotantes temporales.
     * - containerColor: Para definir el color de fondo de toda la pantalla.
     * - content: El bloque de código (lambda) donde va el contenido principal de la pantalla,
     *   el cual recibe un parámetro 'paddingValues' obligatorio para respetar los márgenes.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldScreen() {
        // Scaffold provee la estructura básica de Material Design (ideal para barras, botones flotantes, etc.)
        Scaffold(
            // barra de navegacion superior, incluye el btn hamburguesa, titulo de la app y btn de busqueda.
            topBar = {
                /*
                TopAppBar(
                    // titulo de la aplicacion
                    title = {
                        Text(text = "Mi aplicación")
                    },
                    // btn y menú hamburguesa
                    navigationIcon = {
                        IconButton(onClick = { /* accion del menú*/ }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    },
                    // btn de busqueda
                    actions = {
                        IconButton(
                            onClick = {/* accion de busqueda*/}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor =  MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor =  MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor =  MaterialTheme.colorScheme.onPrimary
                    )

                )
                */
                //TopBarEjemplo() // título alineado a la izquierda
                CenterAlignedTopBarEjemplo() // título centrado
            },

            bottomBar = {
                // barra inferior
                BottomAppBarEjemplo()
            },
            floatingActionButton = {
                // botones flotantes, ejemplos
                //FloatingActionButtonEjemplo() //- clasico
                //SmallFloatingActionButtonEjemplo() //- pequeño
                //LargeFloatingActionButtonEjemplo() //- grande
                ExtendedFloatingActionButtonEjemplo() //- con texto e icono
            },
            // Define el color de fondo usando la paleta de colores del tema actual del sistema
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { paddingValues ->
            /*content: El bloque de código (lambda) donde va el contenido principal de la pantalla,
            *   el cual recibe un parámetro 'paddingValues' obligatorio para respetar los márgenes.*/

            // necesarios para que tu contenido no choque con la barra de estado o la barra de navegación.

            // Box es un contenedor que se usa para apilar elementos o alinearlos de forma sencilla
            Box(
                // Los Modifiers cambian el comportamiento o aspecto de un componente
                modifier = Modifier
                    .fillMaxSize() // Hace que el Box ocupe todo el espacio disponible en la pantalla
                    .padding(paddingValues), // Aplica los márgenes de seguridad calculados por el Scaffold

                // Alinea todoo el contenido interno exactamente en el centro del Box
                contentAlignment = Alignment.Center
            ) {
                // Text es el componente básico para mostrar cadenas de caracteres en la pantalla
                Text(text = "Contenido principal.")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarEjemplo(){
        TopAppBar(
            // titulo de la aplicacion
            title = {
                Text(text = "Mi aplicación")
            },
            // btn y menú hamburguesa
            navigationIcon = {
                IconButton(onClick = { /* accion del menú*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú"
                    )
                }
            },
            // btn de busqueda
            actions = {
                IconButton(
                    onClick = {/* accion de busqueda*/}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor =  MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor =  MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor =  MaterialTheme.colorScheme.onPrimary
            )

        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CenterAlignedTopBarEjemplo(){
        CenterAlignedTopAppBar(
            // titulo de la aplicacion
            title = {
                Text(text = "Mi aplicación")
            },
            // btn y menú hamburguesa - Aquí los iconos que se muestran a la izquierda de la navbar
            navigationIcon = {
                IconButton(onClick = { /* accion del menú*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú"
                    )
                }
            },
            // btn de busqueda - Aquí los iconos que se muestran a la derecha de la navbar
            actions = {
                IconButton(
                    onClick = {/* accion de busqueda*/}
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones"
                    )
                }
                IconButton(
                    onClick = {/* accion de busqueda*/}
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración"
                    )
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor =  MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor =  MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor =  MaterialTheme.colorScheme.onPrimary
            )

        )
    }

    @Composable
    fun BottomAppBarEjemplo(){
        BottomAppBar(
            actions = {
                IconButton(onClick = { /* accion de inicio*/ }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio" // para los lectores de pantalla
                    )
                }

                IconButton(onClick = { /* accion */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }

                IconButton(onClick = { /* accion 2*/ }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favoritos"
                    )
                }
            },
            tonalElevation = 6.dp, // sombra para dar profundidad
            windowInsets = WindowInsets.navigationBars, // para mostrarlo correctamente sobre la barra del sistema < o ...
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    }


    // ejemplos de botones flotantes
    @Composable
    fun FloatingActionButtonEjemplo(){
        FloatingActionButton(
            onClick = { /* Accion que se ejecuta al presionar el btn */},
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 12.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar"
            )
        }
    }

    @Composable
    fun SmallFloatingActionButtonEjemplo(){
        SmallFloatingActionButton(
            onClick = { /* Accion que se ejecuta al presionar el btn */},
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 12.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar"
            )
        }
    }

    @Composable
    fun LargeFloatingActionButtonEjemplo(){
        LargeFloatingActionButton(
            onClick = { /* Accion que se ejecuta al presionar el btn */},
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 12.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar"
            )
        }
    }

    @Composable
    fun ExtendedFloatingActionButtonEjemplo(){
        var isExpanded by remember { mutableStateOf(true) }

        ExtendedFloatingActionButton(
            onClick = {
                println("EFAB presionado.")
                isExpanded = !isExpanded
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Enviar"
                )
            },
            text = {
                Text(text = "Enviar mensaje")
            },
            expanded = isExpanded,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(16.dp),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 12.dp
            )
        )
    }

}

