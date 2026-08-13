// Define que este archivo pertenece a la capa visual (vistas) de tu proyecto
package com.example.mvvmejemplo.view

// IMPORTACIONES DE JETPACK COMPOSE: Herramientas para diseñar la interfaz gráfica
import androidx.compose.foundation.layout.Column // Contenedor para alinear elementos verticalmente
import androidx.compose.foundation.layout.Spacer // Espacio vacío para separar componentes
import androidx.compose.foundation.layout.fillMaxSize // Modificador para ocupar todo el ancho y alto disponible
import androidx.compose.foundation.layout.fillMaxWidth // Modificador para ocupar todo el ancho disponible
import androidx.compose.foundation.layout.height // Modificador para definir una altura fija
import androidx.compose.foundation.layout.padding // Modificador para añadir márgenes internos
import androidx.compose.foundation.lazy.LazyColumn // Lista de desplazamiento vertical eficiente (solo dibuja lo que se ve en pantalla)
import androidx.compose.foundation.lazy.items // Función para recorrer los datos y renderizarlos en la LazyColumn
import androidx.compose.material3.Card // Tarjeta visual con bordes redondeados y sombra
import androidx.compose.material3.ExperimentalMaterial3Api // Anotación requerida para usar componentes Material 3 modernos
import androidx.compose.material3.MaterialTheme // Provee los colores y tipografías globales del tema de la app
import androidx.compose.material3.Scaffold // Estructura base que organiza barras superiores, inferiores y contenido
import androidx.compose.material3.Text // Componente para mostrar textos en la pantalla
import androidx.compose.material3.TopAppBar // La barra superior de título de la aplicación
import androidx.compose.material3.TopAppBarDefaults // Configuración por defecto para los estilos de la barra superior
import androidx.compose.runtime.Composable // Indica que esta función dibuja una interfaz visual en Jetpack Compose
import androidx.compose.ui.Modifier // Herramienta para modificar el diseño, tamaño o comportamiento de los componentes
import androidx.compose.ui.text.font.FontWeight // Define el grosor de la letra (Negrita, normal, etc.)
import androidx.compose.ui.unit.dp // Unidad de medida (Density-independent Pixels) para márgenes y tamaños
import androidx.compose.ui.unit.sp // Unidad de medida (Scale-independent Pixels) exclusiva para fuentes de texto
import androidx.lifecycle.viewmodel.compose.viewModel // Función para instanciar e inyectar el ViewModel automáticamente
import com.example.mvvmejemplo.viewmodel.ProductoViewModel // Importa el ViewModel que maneja los datos de los productos


/************************************************************
 *
 * EXPLICACION IMPORTANTE ABAJO DEL CODIGO
 *
 * ***********************************************************/


// Indica al compilador que estamos de acuerdo con usar la API experimental de la TopAppBar de Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosPantalla(
    // Instancia el ViewModel. Si ya existe uno en este ciclo de vida, lo reutiliza automáticamente
    viewModel: ProductoViewModel = viewModel() // parámetros por defecto de Kotlin e inferencia de tipos. Es decir: nombreVariable : TipoObjeto = ValorPorDefecto.
){
    // Lee la variable de estado 'productos' de tu ViewModel. Al usar 'by' en el ViewModel,
    // esta pantalla se redibujará sola automáticamente en cuanto la lista cambie.
    val productos = viewModel.productos

    // Estructura base de la pantalla
    Scaffold(
        // Definimos la barra superior de nuestra aplicación
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Ejemplo MVVM") // Texto que se mostrará en la barra
                },
                // Configuramos los colores de la barra usando los definidos en el tema de la app
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo (Color principal)
                    titleContentColor = MaterialTheme.colorScheme.onPrimary // Color del texto (Contraste sobre el principal)
                )
            )
        }
    ) { paddingValues -> // 'paddingValues' calcula el espacio que ocupa la barra superior para que el contenido no se tape

        // Contenedor principal del contenido, alinea todo hacia abajo (vertical)
        Column(
            modifier = Modifier
                .fillMaxSize() // Ocupa toda la pantalla debajo de la barra
                .padding(paddingValues) // Aplica el margen automático para no quedar debajo de la TopAppBar
                .padding(16.dp) // Añade un margen interno general de 16dp en los cuatro lados
        ) {
            // Título de la sección
            Text(
                text = "Lista de productos",
                style = MaterialTheme.typography.headlineMedium, // Aplica el estilo predefinido para títulos medianos
                fontSize = 18.sp, // Sobrescribe el tamaño de la letra a 18sp
                fontWeight = FontWeight.Bold // Hace que la letra sea negrita
            )

            // Añade un espacio de separación vertical de 16dp entre el título y la lista
            Spacer(modifier = Modifier.height(16.dp))

            // Lista optimizada para mostrar los productos
            LazyColumn {
                // Recorre tu lista de productos cargada desde el ViewModel
                items(productos) { producto ->
                    // Contenedor tipo tarjeta para cada producto individual
                    Card(
                        modifier = Modifier
                            .fillMaxWidth() // Hace que la tarjeta ocupe todo el ancho disponible
                            .padding(vertical = 8.dp) // Deja un espacio de 8dp arriba y abajo entre tarjetas
                    ){
                        // Organiza los textos dentro de la tarjeta uno debajo del otro
                        Column(
                            modifier = Modifier.padding(16.dp) // Margen interno para que el texto no toque los bordes de la tarjeta
                        ) {
                            // Muestra el nombre del producto actual
                            Text(
                                text = producto.nombre,
                                style = MaterialTheme.typography.titleMedium // Estilo mediano para resaltar el nombre
                            )
                            // Muestra el precio del producto actual concatenando el texto "USD"
                            Text(
                                text = "Precio: ${producto.precio} USD",
                                style = MaterialTheme.typography.titleMedium
                            )
                        } // Cierre del Column de la tarjeta
                    } // Cierre del Card
                } // Cierre del bucle items
            } // Cierre de la LazyColumn
        } // Cierre del Column principal
    } // Cierre del Scaffold
} // Cierre de la función ProductosPantalla





/*
* IMPORTANTE:
*
*
* 🤔 PREGUNTA: ¿Sería igual escribir:
*    viewModel: ProductoViewModel = viewModel()
*    que escribir:
*    viewModel: ProductoViewModel = ProductoViewModel() ?
*
* 🛑 RESPUESTA: ¡No del todoo! Este es uno de los secretos más importantes de Android.
* Aunque visualmente se parecen, hacen cosas muy diferentes con la memoria del teléfono.
* Si usaras ProductoViewModel(), tu aplicación compilaría, pero romperías el ViewModel.
*
* --------------------------------------------------------------------------------------
* ❌ CASO 1: Si usas ProductoViewModel() (Creación manual tradicional)
* --------------------------------------------------------------------------------------
* - Estás creando una instancia común y corriente de la clase.
* - EL PROBLEMA: Cada vez que la pantalla de Android se redibuje (por ejemplo, si el
*   usuario rota el teléfono o cambia el tema oscuro/claro), la pantalla se destruye
*   y se vuelve a crear por completo.
* - CONSECUENCIA: El bloque 'init' se ejecutaría una y otra vez. Tu lista de productos
*   se borraría y se volvería a cargar desde cero, perdiendo los datos en memoria.
*
* --------------------------------------------------------------------------------------
* ✅ CASO 2: Si usas viewModel() (La función inteligente de Jetpack Compose)
* --------------------------------------------------------------------------------------
* - Esta función (con 'v' minúscula) NO es el constructor. Es un administrador de Android.
* - Cuando la pantalla se ejecuta, la función viewModel() hace esto tras bambalinas:
*   1. Va al almacén del sistema de Android y pregunta: ¿Ya existe una instancia
*      de 'ProductoViewModel' viva para esta pantalla?
*   2. Si NO existe (Primera vez que abre la app): Llama a ProductoViewModel() en privado,
*      crea la instancia, la guarda en el almacén del sistema y te la entrega.
*   3. Si SÍ existe (Porque el usuario rotó la pantalla): No crea nada nuevo. Va al
*      almacén, saca la instancia que ya tenía los productos cargados y te la devuelve intacta.
*
* --------------------------------------------------------------------------------------
* 💡 ANALOGÍA DE LA VIDA REAL
* --------------------------------------------------------------------------------------
* - Hacer ProductoViewModel() es como ir a la agencia a COMPRAR UN CARRO NUEVO cada vez
*   que necesitas salir de casa. Es costoso y siempre empiezas con el odómetro en cero.
* - Hacer viewModel() es como ir al estacionamiento de tu casa y BUSCAR EL CARRO QUE YA TIENES.
*   Si ya hay uno ahí guardado, usas ese; si el estacionamiento está vacío (porque es tu
*   primera vez en la casa), compras uno y lo dejas ahí guardado para la próxima.
*
* CONCLUSIÓN: En Android nunca inicializamos los ViewModels llamando a su constructor
* directamente con Clase(), sino que dejamos que la función viewModel() gestione su ciclo de vida.
*/