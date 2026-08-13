// Define la ubicación del archivo en el proyecto para que Kotlin sepa dónde encontrarlo
package com.example.mvvmejemplo

// IMPORTACIONES: Traen herramientas del sistema Android y de Jetpack Compose
import android.os.Bundle // Objeto para guardar y recuperar el estado de la pantalla
import androidx.activity.ComponentActivity // Clase base moderna de Android para manejar pantallas
import androidx.activity.compose.setContent // Función de Compose para renderizar interfaces sin usar archivos XML
import androidx.activity.enableEdgeToEdge // Función para que la app ocupe toda la pantalla (pantalla completa/borde a borde)

// IMPORTACIONES PROPIAS: Traen el código de diseño y vistas que tú creaste en otras carpetas
import com.example.mvvmejemplo.ui.theme.MVVMEjemploTheme // El tema de diseño (colores, fuentes, formas de la app)
import com.example.mvvmejemplo.view.ProductosPantalla // La función que dibuja la interfaz de usuario de los productos

// MainActivity hereda (:) de ComponentActivity para obtener todas las funciones de una pantalla de Android
class MainActivity : ComponentActivity() {

    // override indica que estamos sobrescribiendo la función onCreate del padre (ComponentActivity)
    // Se ejecuta automáticamente por el sistema operativo cuando el usuario abre la aplicación
    override fun onCreate(savedInstanceState: Bundle?) {

        // Llama primero a la lógica interna de la clase padre para configurar el inicio básico de la ventana
        super.onCreate(savedInstanceState)

        // Configura la app para que se dibuje debajo de la barra de estado (batería/hora) y de navegación
        enableEdgeToEdge()

        // Bloque principal de Jetpack Compose; define qué componentes visuales se van a renderizar
        setContent {

            // Aplica la configuración estética y de marca (colores claros/oscuros, tipografías) a todo lo que esté adentro
            MVVMEjemploTheme {

                // Llama e inserta el componente visual principal que creaste para mostrar los productos
                ProductosPantalla()

            } // Cierre del Tema de la App
        } // Cierre del setContent
    } // Cierre de la función onCreate
} // Cierre de la clase MainActivity


/*
* Explicacion general de estructura de carpetas para MVVM
*
* com.example.mvvmejemplo/
│
├── data/                  📦 CAPA DE DATOS (El origen del contenido)
│   ├── model/             📂 Modelos de datos puros
│   │   └── Producto.kt    -> Clase de datos (data class) que define qué campos tiene un Producto (id, nombre, precio).
│   └── network/           📂 Conexiones externas (APIs o Bases de datos)
│       └── ProductoService.kt -> Código para conectarse a internet y descargar la lista de productos de un servidor.
│
├── viewmodel/             🧠 CAPA VIEWMODEL (El cerebro / Puente de comunicación)
│   └── ProductosViewModel.kt -> Controla la lógica de la pantalla. Le pide los datos a la capa 'data',
│                                maneja estados (cargando, éxito, error) y los expone listos para la pantalla.
│
├── view/                  🎨 CAPA VIEW (La interfaz gráfica)
│   └── ProductosPantalla.kt  -> Contiene funciones @Composable. Solo se encarga de pintar botones, listas y textos.
│                                Observa lo que expone el ViewModel y reacciona de forma automática.
│
└── ui.theme/              🖌️ CAPA DE DISEÑO (Viene por defecto en Compose)
    ├── Color.kt           -> Paleta de colores de la app.
    ├── Theme.kt           -> Configuración de temas oscuros/claros de Jetpack Compose.
    └── Type.kt            -> Tipografías y fuentes de texto.

*
* 🔁 ¿Cómo se comunican entre sí?
*
* 1- La View (Pantalla) detecta que el usuario quiere ver productos
* y le avisa al ViewModel.
*
* 2- El ViewModel le pide la información a la capa Data
* (que puede traerlos de internet o de una base de datos local).
*
* 3- Data le responde al ViewModel con los Models (objetos Producto).
*
* 4- El ViewModel actualiza su estado interno y la View, que está "escuchando"
* ese estado, se redibuja en milisegundos con los nuevos datos en la pantalla
* del usuario.
* */