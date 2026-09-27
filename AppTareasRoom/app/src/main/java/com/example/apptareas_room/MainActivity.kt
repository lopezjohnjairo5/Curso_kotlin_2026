package com.example.apptareas_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.apptareas_room.navigation.AppNavigation
import com.example.apptareas_room.ui.agregar_tarea.AgregarTareaScreen
import com.example.apptareas_room.ui.lista_tareas.ListaTareasScreen
import com.example.apptareas_room.ui.theme.AppTareasRoomTheme
import com.example.apptareas_room.ui.theme.ThemeMode
import com.example.apptareas_room.ui.viewmodel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // indica que se requiere inyeccion de dependencias
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*
                * NOTAS:
                * - HILT : biblioteca oficial de inyección de dependencias para Android creada por Google y construida sobre Dagger.
                * - ROOM : biblioteca oficial de Android Jetpack que facilita el manejo de bases de datos locales en aplicaciones desarrolladas con Kotlin.
                * - las entidades son las tablas, es decir las estructuras de las tablas de la base de datos
                * - DAO es la interface de acceso a datos o Data Access Objetc, es aqui donde se crea el CRUD
                *
                *
                * */

            /**
             * =============================================================================
             * 📝 GUÍA DE ARQUITECTURA: APLICACIÓN DE TAREAS (ROOM + MVVM + HILT + COMPOSE)
             * =============================================================================
             *
             * Esta aplicación sigue las mejores prácticas de Google (Clean Architecture / MVVM),
             * separando las responsabilidades en capas independientes y fáciles de mantener.
             *
             * -----------------------------------------------------------------------------
             * 📂 1. CAPA DE DATOS (data) -> Gestión de la Base de Datos Local
             * -----------------------------------------------------------------------------
             * - [TareaEntity]: Define la estructura de la tabla (columnas como ID, título, etc.).
             * - [TareaDao]: Interfaz con las consultas SQL (Insertar, Eliminar, Actualizar, Buscar).
             * - [TareaDatabase]: Configura Room y sirve de puente principal a la base de datos.
             * - [TareaRepository]: El mediador. El resto de la app solo le pide datos a él,
             *   ocultando si la información viene de Room o de una API de internet.
             *
             * -----------------------------------------------------------------------------
             * 📂 2. CAPA DE INYECCIÓN DE DEPENDENCIAS (di) -> Control de Instancias
             * -----------------------------------------------------------------------------
             * - [AppModule]: Provee de forma automática objetos complejos (Database, Repository)
             *   donde la app los necesite, evitando tener que crearlos manualmente con 'val'.
             *
             * -----------------------------------------------------------------------------
             * 📂 3. CAPA DE PRESENTACIÓN (ui & viewmodel) -> Lo que el usuario ve y hace
             * -----------------------------------------------------------------------------
             * - [TareaViewModel]: El cerebro de la UI. Pide datos al Repositorio, mantiene el
             *   estado de la pantalla y sobrevive a cambios de configuración (como girar el móvil).
             * - [lista_tareas / ListaTareasScreen]: Pantalla Compose que muestra todas las tareas.
             * - [lista_tareas / TareaItem]: El diseño visual (tarjeta/fila) de una sola tarea.
             * - [agregar_tarea / AgregarTareaScreen]: Formulario para registrar nuevas tareas.
             * - [editar_tarea / EditarTareaScreen]: Formulario para modificar tareas existentes.
             * - [theme]: Contiene (Color, Theme, Type) para definir estilos y colores globales.
             *
             * -----------------------------------------------------------------------------
             * 📂 4. NAVEGACIÓN Y ARCHIVOS RAÍZ
             * -----------------------------------------------------------------------------
             * - [AppNavigation.kt]: Mapa de rutas. Controla cómo saltar entre pantallas.
             * - [Utils.kt]: Herramientas genéricas compartidas (ej. formatear fechas).
             * - [MainActivity.kt]: Actividad principal. Aloja el contenedor donde corre Compose.
             * - [TareaApp.kt]: Clase Application. Enciende el motor de Hilt (@HiltAndroidApp).
             *
             * =============================================================================
             * 🔄 FLUJO DE DATOS (¿Cómo se conectan entre sí?)
             * =============================================================================
             * Cuando el usuario interactúa, la información viaja en este orden estricto:
             *
             *   [Composables (UI)] ──> Piden actualización o acción a...
             *     └── [TareaViewModel] ──> Gestiona la lógica y se comunica con...
             *           └── [TareaRepository] ──> Solicita los datos a...
             *                 └── [TareaDao] ──> Ejecuta la consulta en...
             *                       └── [TareaDatabase (Room)]
             *
             * Los datos regresan en sentido inverso en forma de Estados reactivos hacia la UI.
             */
            AppRoot()
        }
    }
}

// raiz de la aplicacion, sirve para inicializar todoo lo necesario de la aplicacion, como el tema o la navegacion
@Composable
fun AppRoot(){
    val vm : ThemeViewModel = hiltViewModel()
    val mode = vm.themeMode.collectAsState().value

    val isDark = when(mode){
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    AppTareasRoomTheme(darkTheme = isDark, dynamicColor = true) {
        // creamos una instancia del controlador de navegacion
        val navController = rememberNavController() // conservamos una unica instancia gracias al remember
        AppNavigation(navController = navController) // contiene la navegacion y le pasamos el navcontroller
    }
}