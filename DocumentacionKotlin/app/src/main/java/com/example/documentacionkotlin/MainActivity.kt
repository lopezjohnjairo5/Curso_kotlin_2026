package com.example.documentacionkotlin
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateMapOf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import com.example.documentacionkotlin.ui.theme.DocumentacionKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocumentacionKotlinTheme {
                /*
                * Aqui se habla un poco de la forma recomendada de comentar una aplicacion
                * hecha en kotlin.
                *
                * - Para documentar aplicaciones en Kotlin de forma profesional,
                *   el estándar oficial y la forma correcta de hacerlo es utilizando
                *   Dokka (herramienta de generación de documentación oficial de
                *   Jetpack / Kotlin creada por JetBrains, equivalente a Javadoc para Java)
                *
                * El Formato: ¿Cómo escribir la documentación?
                *
                * - los comentarios en el código deben ser en formato KDoc. es decir usando barras
                *   y doble asterisco \/** ... *\/ (NOTA: aqui se pone \\ para evitar que se cierre el comentario actual, es decir el de la explicacion)
                * - Los comentarios inician con una descripcion de lo que hace el modulo, clase o funcion
                *   si se requiere enlazar con otros metodos o funciones de codigo se ponen estas entre corchetes rectos []
                * - Luego se ponen:
                *   - @param: Datos que entran a la funcion, metodo, clase, ej: @param MiFuncion Esta es la explicacion de la funcion
                *   - @return: Datos que salen de la funcion, metodo, clase
                *   - @property: Describe las propiedades o variables globales de una clase o los campos de una Data Class,
                *        o en general variables internas de la funcion, metodo o clase a documentar.
                *
                *   - @author: nombre del desarrollador que está trabajando en el codigo
                *   - @since: indica en qué versión de la aplicación, librería o módulo se introdujo por primera vez una clase,
                *       función o propiedad específica.
                *   - @see: para crear enlaces de referencia cruzada hacia otras clases, funciones, archivos o incluso páginas web externas.
                *           - Ejemplos:
                *                - @see [UsuariosViewModel] // <- Enlaza directamente a la clase de la lógica
                *                - @see [UsuariosViewModel.eliminarUsuarioPorId] // <- Enlaza a una función específica
                *                - @see [Documentación Oficial de LazyColumn](https://android.com) // <- Enlaza a una función específica
                *
                *
                *
                * - NOTAS:
                *    - No se debe poner el nombre de la funcion, metodo o clase que se esta comentando, dentro del comentario ya que automaticamente
                *      la documentacion toma dicho nombre del bloque de codigo que se encuentra inmediatamente despues de la documentacion actual.
                *    - Los comentarios se deben poner antes de los metodos, funciones, clases que se estan documentando.
                */*/

                /*
                 * ============================================================================
                 * GUÍA DE REFERENCIA: CONFIGURACIÓN DE DOCUMENTACIÓN Y COPYRIGHT EN DOKKA V2
                 * ============================================================================
                 *
                 * Este comentario sirve como plantilla para recordar cómo configurar las opciones
                 * de propiedad intelectual, autoría y generación automática de documentación.
                 *
                 * IMPORTANTE
                 *
                 * - PARA MAS INFORMACION VER LOS ARCHIVOS (build.gradle.kts (module y app), libs.versions.toml) DE ESTE MISMO PROYECTO
                 * - Para crear la documentacion el proyecto no debe tener errores ya que de lo contrario NO se podrá crear la documentacion
                 *
                 * 1. EN EL ARCHIVO DE CATÁLOGO: gradle/libs.versions.toml
                 *
                 * ----------------------------------------------------------------------------
                 * En la sección [versions], definir la versión estable de Dokka:
                 * dokka = "2.2.0"
                 *
                 * En la sección [plugins], declarar el ID oficial usando guiones medios:
                 * jetbrains-dokka = { id = "org.jetbrains.dokka", version.ref = "dokka" }
                 *
                 * 2. EN EL ARCHIVO RAÍZ: build.gradle.kts (Project: DocumentacionKotlin)
                 * ----------------------------------------------------------------------------
                 * Registrar el alias. Nota: Gradle cambia los guiones del TOML por puntos (.)
                 * plugins {
                 *     alias(libs.plugins.android.application) apply false
                 *     alias(libs.plugins.kotlin.compose) apply false
                 *     alias(libs.plugins.jetbrains-dokka) apply false
                 *     // Ojo: Si usas la versión antigua de alias, se escribe: alias(libs.plugins.jetbrains.dokka)
                 * }
                 *
                 * 3. EN EL ARCHIVO DEL MÓDULO: build.gradle.kts (:app) [CONFIGURACIÓN DE COPYRIGHT]
                 * ----------------------------------------------------------------------------
                 * plugins {
                 *     alias(libs.plugins.android.application)
                 *     alias(libs.plugins.kotlin.compose)
                 *     alias(libs.plugins.jetbrains-dokka)
                 * }
                 *
                 * dependencies {
                 *     // Obligatorio para evitar errores visuales al usar viewModel() en Compose:
                 *     implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
                 * }
                 *
                 * // -------------------------------------------------------------------------
                 * // BLOQUE DE AVISO DE PRIVACIDAD Y COPYRIGHT GLOBAL (DOKKA V2)
                 * // -------------------------------------------------------------------------
                 * // Se calcula el año actual de forma dinámica y se inyecta en el pie de página
                 * // (footer) de cada una de las páginas web que genere la documentación.
                 * // Nota: Es estrictamente obligatorio usar comillas dobles (""") para la interpolación.
                 *
                 * val añoActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
                 *
                 * tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
                 *     pluginsMapConfiguration.set(
                 *         mapOf("org.jetbrains.dokka.base.DokkaBase" to """{ "footerMessage": "© $añoActual TuNombre / TuEmpresa. Todos los derechos reservados." }""")
                 *     )
                 * }
                 *
                 * 4. EN EL CÓDIGO FUENTE (Buenas prácticas de Autoría y Copyright en KDoc):
                 * ----------------------------------------------------------------------------
                 * Adicional al pie de página global, cada archivo debe registrar a su autor original
                 * utilizando la etiqueta '@author'.
                 *
                 * /**
                 *  * Descripción principal de la pantalla o clase (Evitar repetir el nombre aquí).
                 *  *
                 *  * @param modifier Modificador de Compose para ajustar el diseño.
                 *  * @property datosInternos Si es una clase, describe sus variables globales.
                 *  * @author Tu Nombre Completo o Nombre de la Organización
                 *  * @since 1.0.0
                 *  * @see [NombreDeOtraClaseRelacionada] <- Crea un enlace web interactivo
                 *  */
                 * @Composable
                 * fun MiComponente(modifier: Modifier = Modifier) { ... }
                 *
                 * 5. COMANDO DE GENERACIÓN DE DOKKA V2
                 * ----------------------------------------------------------------------------
                 * Para evitar errores de JAVA_HOME en la terminal de Windows, presionar la tecla
                 * 'Ctrl' DOS VECES rápidamente (Ctrl + Ctrl) para abrir la ventana "Run Anything"
                 * de Android Studio y ejecutar exactamente:
                 *
                 * gradle dokkaGenerateHtml
                 *
                 * 6. ¿DÓNDE ENCONTRAR LA PÁGINA WEB GENERADA?
                 * ----------------------------------------------------------------------------
                 * Una vez que termine la compilación con "BUILD SUCCESSFUL":
                 * 1. Cambiar la vista del explorador de archivos izquierdo de "Android" a "Project".
                 * 2. Ir a la ruta: app -> build -> dokka -> html.
                 * 3. Clic derecho sobre 'index.html' -> Open in -> Browser (y elegir navegador).
                 * ============================================================================
                 */


                // A continuacion un ejemplo simple de documentacion

                /**
                 * Componente que muestra una lista mutable de usuarios que persiste tras la rotación de pantalla.
                 *
                 * Utiliza [rememberSaveable] con un convertidor personalizado para retener las claves numéricas.
                 *
                 * @param modifier Modificador de Compose para ajustar el diseño visual de la pantalla.
                 * @property users El mapa mutable que almacena los ID de los usuarios y sus nombres.
                 * @author Tu Nombre
                 * @since 1.0.0
                 */
            }
        }
    }





    // Ejemplo mas completo de documentacion NOTA: REQUIERE IMPORTACIONES Y DEMÁS PARA PODER FUNCIONAR EL CODIGO KOTLIN, PERO SE PONE SOLO COMO EJEMPLO DE DOCUMENTACION

    /**
     * Entidad que representa la estructura de un usuario en el sistema.
     *
     * Esta clase es inmutable y sirve como el modelo de datos principal para las
     * operaciones del listado.
     *
     * @property id El identificador único del usuario (Clave primaria).
     * @property nombre El nombre completo del usuario registrado.
     *
     * @author Tu Nombre Completo
     * @since 1.0.0
     * @see UsuariosViewModel
     */
    data class UsuarioModel(
        val id: Int,
        val nombre: String
    )

    /**
     * Componente de arquitectura encargado de la lógica de negocio de los usuarios.
     *
     * Mantiene un estado mutable que sobrevive a los cambios de configuración y
     * rotación de pantalla de Android gracias al ciclo de vida del [ViewModel].
     *
     * @property users Mapa reactivo indexado por ID que contiene los objetos [UsuarioModel].
     * @property nombresDisponibles Pool de nombres aleatorios utilizados para las inserciones de prueba.
     *
     * @author Tu Nombre Completo
     * @since 1.0.0
     */
    class UsuariosViewModel : ViewModel() {

        val users = mutableStateMapOf(
            1 to UsuarioModel(1, "John"),
            2 to UsuarioModel(2, "Maria")
        )

        private val nombresDisponibles = listOf("Armando", "Beatriz", "Camilo", "Doris", "Edwin")

        /**
         * Genera un nuevo usuario con un ID incremental y un nombre al azar.
         *
         * El cálculo del ID busca el valor máximo actual y le suma 1. Si el mapa está
         * vacío, inicia en 1.
         *
         * @return El objeto [UsuarioModel] que fue recién creado e insertado en el mapa.
         * @since 1.0.0
         */
        fun agregarUsuarioAleatorio(): UsuarioModel {
            val nuevoId = (users.keys.maxOrNull() ?: 0) + 1
            val nombreAlazar = nombresDisponibles.random()
            val nuevoUsuario = UsuarioModel(nuevoId, nombreAlazar)

            users[nuevoId] = nuevoUsuario
            return nuevoUsuario
        }

        /**
         * Remueve un usuario del estado global utilizando su identificador único.
         *
         * @param id El identificador numérico del usuario que se desea eliminar.
         * @since 1.1.0  // <- Indica que esta función se añadió en una actualización posterior
         */
        fun eliminarUsuarioPorId(id: Int) {
            users.remove(id)
        }
    }

    /**
     * Pantalla principal que renderiza la interfaz de gestión de usuarios en Jetpack Compose.
     *
     * Muestra un botón superior de acción y un listado dinámico [LazyColumn] limitado
     * visualmente al 70% de la pantalla para optimizar la navegación.
     *
     * ### Ejemplo de uso:
     * ```
     * MiMapaDeUsuariosMutablePersistente(
     *     modifier = Modifier.fillMaxSize()
     * )
     * ```
     *
     * @param modifier Modificador de Compose para personalizar el diseño del contenedor exterior.
     * @param viewModel Instancia del controlador de estado. Se inyecta por defecto.
     *
     * @author Tu Nombre Completo
     * @since 1.0.0
     */
    @Composable
    fun MiMapaDeUsuariosMutablePersistente(
        modifier: Modifier = Modifier,
        viewModel: UsuariosViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { viewModel.agregarUsuarioAleatorio() }
            ) {
                Text(text = "Agregar nuevo usuario")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // La LazyColumn lee el estado reactivo del ViewModel
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f) // Limita el tamaño físico al 70% de la pantalla
            ) {
                items(viewModel.users.toList()) { (id, usuario) ->
                    // Aquí llamarías a tu componente UserItem de interfaz
                    Text(
                        text = "ID: ${usuario.id} - Nombre: ${usuario.nombre}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }



}
