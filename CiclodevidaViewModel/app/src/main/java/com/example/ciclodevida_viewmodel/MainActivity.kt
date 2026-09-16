package com.example.ciclodevida_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ciclodevida_viewmodel.navigation.AppNavHost
import com.example.ciclodevida_viewmodel.ui.theme.CicloDeVidaViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CicloDeVidaViewModelTheme {
                /* =========================================================================================================================
                   GUÍA RÁPIDA DE ARQUITECTURA: GESTIÓN DE ESTADO Y NAVEGACIÓN EN JETPACK COMPOSE
                   =========================================================================================================================

                   1. remember
                   -------------------------------------------------------------------------------------------------------------------------
                   * ¿Qué es?:         Función de memoria local integrada en Jetpack Compose.
                   * ¿Para qué sirve?: Retener un valor en memoria RAM durante las recomposiciones (redibujados) de la interfaz gráfica.
                   * ¿Cómo se usa?:    val estadoVisible = remember { mutableStateOf(false) }
                   * ¿Cuándo usarlo?:  Para estados visuales muy efímeros y locales (ej. si un desplegable está abierto, animaciones simples).
                   * Diferencia:       Es el más frágil. NO sobrevive a rotaciones de pantalla ni a cambios de pantalla (navegación).

                   2. rememberSaveable
                   -------------------------------------------------------------------------------------------------------------------------
                   * ¿Qué es?:         Una extensión de 'remember' vinculada al mecanismo de almacenamiento 'Bundle' de Android.
                   * ¿Para qué sirve?: Guardar datos sencillos de la interfaz para que no se destruyan al recrear la Activity.
                   * ¿Cómo se usa?:    val textoFormulario = rememberSaveable { mutableStateOf("") }
                   * ¿Cuándo usarlo?:  Para datos de inputs del usuario (ej. texto escrito en un TextField, checkboxes o la posición del scroll).
                   * Diferencia:       SOBREVIVE a rotaciones de pantalla, pero NO a la navegación profunda o cierre de la app. Límite < 1MB.

                   3. ViewModel
                   -------------------------------------------------------------------------------------------------------------------------
                   * ¿Qué es?:         Clase arquitectónica de Jetpack totalmente independiente del ciclo de vida de la interfaz.
                   * ¿Para qué sirve?: Almacenar estados complejos y gestionar la lógica de negocio (llamadas a APIs, bases de datos Room).
                   * ¿Cómo se usa?:    class MiViewModel : ViewModel() { ... } -> Instancia: val vm: MiViewModel = viewModel()
                   * ¿Cuándo usarlo?:  Para listados de red, flujos reactivos (StateFlow) y lógica que requiera persistir durante la sesión.
                   * Diferencia:       SOBREVIVE a rotaciones y a la navegación (mientras la pantalla esté en la pila). Se limpia al salir del flujo.

                   4. NavHost
                   -------------------------------------------------------------------------------------------------------------------------
                   * ¿Qué es?:         Contenedor y orquestador de rutas del componente oficial Jetpack Navigation.
                   * ¿Para qué sirve?: Coordinar el flujo de navegación entre pantallas y controlar el historial de páginas (backstack).
                   * ¿Cómo se usa?:    NavHost(navController, startDestination) { composable("ruta") { Pantalla() } }
                   * ¿Cuándo usarlo?:  Como la columna vertebral o estructura raíz de la aplicación para permitir transiciones de pantallas.
                   * Diferencia:       No guarda variables individuales; se encarga de MANTENER VIVAS o DESTRUIR las pantallas y sus ViewModels.
                   ========================================================================================================================= */

                AppNavHost()
            }
        }
    }
}