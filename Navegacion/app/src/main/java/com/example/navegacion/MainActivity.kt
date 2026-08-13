package com.example.navegacion

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
import com.example.navegacion.ui.theme.NavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacionTheme {
                /*
                * Creamos una funcion | metodo MyApp, este se encarga del Scaffold y de invocar
                * al NavGrap, el cual a su vez establece las rutas de navegacion dentro de la aplicacion,
                * en cada una de las rutas se llama al Composable que tiene el contenido y se le pasa un nombre
                * mediante composable("nombrePantalla"), esto permite identificar y asociar los botones
                * que al ser presionados llevaran a dicha pantalla:
                * Button(
                *  onClick = { navController.navigate("nombrePantalla") }
                * )
                * El flujo de la aplicacion se basa en LIFO -> Last Input First Output, o ultimo en entrar primero en salir
                * los metodos que renderizan una pantalla requieren un navController siempre y cuando
                * desde dicha pantalla se pueda ir a otra pantalla, en caso de ser la ultima pantalla
                * no será necesario pasarle el navController.
                *
                * NOTAS:
                * - para usar navController es necesario incluirlo en libs.version.toml
                *  [versions] -> navControllerV = "2.9.0"
                *  [libraries] -> navController = { group = "androidx.navigation", name="navigation-compose", version.ref="navControllerV"}
                *
                * - Si se requiere usar botoneras inferiores o superiores (las del Scaffold) y ademas
                * cambios de pantallas se podria hacer dicha combinacion
                * controlando el flujo de la siguiente manera:
                * - las secciones de la botonera NO se cargarian mediante rutas del NavGrap ya que
                * estas serian basicamente parte de la misma pagina, pero si se requiere una pagina
                * fuera como settings (no pertenece a la botonera), esta SI se podria cargar
                * mediante el NavGrap.
                *
                * */
                MyApp()
            }
        }
    }
}