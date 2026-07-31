// Define el paquete del proyecto para organizar tus archivos de código
package com.example.componentesestructurales

// Bloque de importaciones: Trae las herramientas necesarias de Android y Jetpack Compose
import android.R.attr.navigationIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Importa el tema visual generado automáticamente para tu aplicación
import com.example.componentesestructurales.ui.theme.ComponentesEstructuralesTheme
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.nio.file.WatchEvent

// MainActivity es la pantalla o ventana principal donde inicia tu aplicación de Android
class MainActivity : ComponentActivity() {

    // onCreate es el primer método que se ejecuta cuando el sistema operativo crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura la pantalla para que el contenido ocupe todo el espacio físico (pantalla completa sin bordes)
        enableEdgeToEdge()

        // setContent define que la interfaz de usuario se construirá usando bloques de Jetpack Compose
        setContent {

            /**
             * =================================================================================
             * GUÍA DE REFERENCIA CONTEXTUAL DE COMPONENTES ESTRUCTURALES (MATERIAL DESIGN 3)
             * =================================================================================
             *
             * 1. MODAL NAVIGATION DRAWER (Menú Lateral Desplegable)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Es un panel deslizante oculto que emerge desde el
             *   borde lateral de la pantalla. Sirve para agrupar destinos de navegación secundarios,
             *   perfiles de usuario o configuraciones globales del sistema.
             * - Cuándo usarlo: En aplicaciones de tamaño mediano/grande con más de 5 secciones principales,
             *   o cuando necesitas un espacio limpio para mostrar datos del usuario (avatar, correo).
             * - Cuándo NO usarlo: En aplicaciones simples de una o dos pantallas, o en flujos rígidos
             *   paso a paso (como un carrito de compras o un registro) donde el menú distraiga al usuario.
             * - ¿Va en todas las pantallas?:
             *   * SÍ: Si el usuario debe tener la capacidad de invocarlo mediante gestos táctiles
             *     desde cualquier lugar. En este caso, se declara como RAÍZ ABSOLUTA en el contenedor
             *     principal (por fuera de los Scaffolds).
             *   * NO: Si el juego o aplicación tiene zonas aisladas (como la partida en curso) donde
             *     el arrastre lateral accidental arruinaría la experiencia táctil.
             *
             * 2. NAVIGATION RAIL (Barra de Navegación Vertical Lateral)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Es un menú vertical compacto y fijo que se posiciona
             *   a la izquierda de la interfaz. Sirve como el eje de navegación principal en pantallas anchas.
             * - Cuándo usarlo: Diseños exclusivos para Tabletas (modo horizontal), Dispositivos
             *   Plegables (Foldables) o aplicaciones que corren en Computadoras/Escritorio.
             * - Cuándo NO usarlo: En teléfonos móviles estándar en posición vertical (Portrait),
             *   ya que consume espacio horizontal crítico para el contenido.
             * - ¿Va en todas las pantallas?:
             *   * SÍ: En aplicaciones adaptativas complejas donde actúa como el contenedor raíz horizontal
             *     (dentro de un Row global) para conmutar todas las vistas del sistema.
             *   * NO: Si hay pantallas cinemáticas a pantalla completa (como cinemáticas de juegos o login)
             *     donde la barra deba desaparecer por completo.
             *
             * 3. NAVIGATION BAR (Barra de Navegación Inferior)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Es la barra inferior clásica de Android que aloja entre
             *   3 y 5 pestañas principales, manteniéndolas al alcance directo del pulgar.
             * - Cuándo usarlo: En teléfonos móviles para la navegación raíz de la aplicación, donde
             *   el usuario requiera saltar constantemente entre las secciones más importantes.
             * - Cuándo NO usarlo: Si tienes más de 5 destinos (se amontonarían los íconos) o en pantallas
             *   que requieran concentración total en el centro de la interfaz.
             * - ¿Va en todas las pantallas?:
             *   * SÍ: Si compartes el mismo menú inferior en toda la aplicación (se declara una sola vez
             *     en el parámetro 'bottomBar' de un Scaffold global).
             *   * NO: En pantallas secundarias o de detalle profundo (ej. ver el detalle de un producto),
             *     ni en zonas de juego donde el usuario pueda pulsar los botones por error mientras juega.
             *
             * 4. FAMILY OF FLOATING ACTION BUTTONS (FAB, Small FAB, Large FAB, Extended FAB)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Botones que "flotan" tridimensionalmente sobre el contenido.
             *   Sirven para promover de manera masiva la acción afirmativa más importante de la pantalla.
             *   * Small FAB (40dp): Para acciones secundarias agrupadas o interfaces hiperdensas.
             *   * FAB Estándar (56dp): El botón predeterminado de Android (ej: "Redactar" o "Agregar").
             *   * Large FAB (96dp): Para máxima accesibilidad o pantallas de gran formato (Tabletas).
             *   * Extended FAB: Variante animada que combina un ícono y un texto descriptivo expansible.
             * - Cuándo usarlos: Cuando existe una única acción clara y primordial que el usuario realiza
             *   repetidamente en esa sección (ej: Crear, Enviar, Buscar, Añadir).
             * - Cuándo NO usarlos: Para acciones negativas (como "Eliminar" o "Cancelar"), o si la pantalla
             *   tiene múltiples acciones con el mismo nivel de importancia (confundiría al usuario).
             * - ¿Va en todas las pantallas?:
             *   * DEFINITIVAMENTE NO: Un FAB es estrictamente CONTEXTUAL. Debe cambiar de forma, de función,
             *     o desaparecer por completo dependiendo de las necesidades de la pantalla actual. Se declara
             *     en los Scaffolds locales de cada sección.
             *
             * 5. BOTTOM APP BAR (Barra de Acciones Inferior Contextual)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Es un contenedor inferior horizontal diseñado para albergar
             *   múltiples botones de herramientas contextuales y, opcionalmente, un FAB anidado.
             * - Cuándo usarlo: En pantallas de edición (ej. editor de fotos, dibujo), herramientas de juego
             *   (Deshacer, Borrador, Pistas en Sudoku), o gestión densa de ítems.
             * - Cuándo NO usarlo: Si tu objetivo principal es saltar entre pantallas independientes de la app
             *   (para eso se usa estrictamente el 'NavigationBar').
             * - ¿Va en todas las pantallas?:
             *   * NO: Es un componente altamente especializado. Solo debe existir en pantallas donde las
             *     herramientas inferiores agilicen la tarea en curso.
             *
             * 6. TOP APP BAR & CENTER ALIGNED TOP APP BAR (Barras Superiores de Aplicación)
             * ---------------------------------------------------------------------------------
             * - ¿Qué es y para qué sirve?: Son las cabeceras de la aplicación. Muestran el título de la
             *   sección actual, accesos rápidos de configuración/búsqueda (derecha) y controles de retorno o menús (izquierda).
             *   * TopAppBar: Título alineado a la izquierda, ideal para pantallas principales o títulos largos.
             *   * CenterAlignedTopAppBar: Título perfectamente centrado, ideal para destinos únicos y vistas estéticas.
             * - Cuándo usarlas: En cualquier pantalla clásica de la app para dar orientación espacial al usuario
             *   y proveer el botón para "Volver atrás" o abrir el menú lateral.
             * - Cuándo NO usarlas: En menús principales de videojuegos, pantallas de carga (Splash Screens), o flujos
             *   cinemáticos inmersivos donde las barras rompan la inmersión visual.
             * - ¿Va en todas las pantallas?:
             *   * SÍ (Forma Global): Si el título nunca cambia drásticamente o se actualiza de forma simple.
             *   * NO (Forma Local/Múltiples Scaffolds): Si tu aplicación combina pantallas de juego libre
             *     (sin barras) con pantallas de configuración o tableros (con barras), cada pantalla debe ser
             *     responsable de declarar su propia barra superior o prescindir de ella.
             *
             * =================================================================================
             * RESUMEN DE ORO DE LA ARQUITECTURA SCAFOLD:
             * Un solo Scaffold Global es perfecto para aplicaciones tradicionales de oficina o gestión (redes
             * sociales, herramientas de productividad, tiendas). Múltiples Scaffolds Locales son el estándar
             * obligatorio para Videojuegos, utilitarios de edición gráfica o aplicaciones multimedia híbridas.
             * =================================================================================
             */

            // Aplica el tema de diseño (colores, tipografías) configurado para tu proyecto
            ComponentesEstructuralesTheme {
                // Llama a la función que dibuja tu pantalla estructurada
                //ScaffoldScreenNavHorizontal() // aqui se muestran los componentes generales
                //ScaffoldScreenNavVertical() // esta es una copia del anterior pero mostrando la barra vertical de navegacion
                //NavigationDrawerEjemplo() // muestra un menú desplegable de izquierda a derecha o al dar clic en la hamburguesa del menú.
                //EjemploJuegoConDrawerApp() // ejemplo de simulacion de pantallas de un juego de Kakuro, para ver el funcionamiento de scaffold y NavigationDrawer.
                TabRowExample()
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
    fun ScaffoldScreenNavHorizontal() {
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
                // barras inferiores

                //BottomAppBarEjemplo()
                NavigationBarEjemplo() // aquí se llama el elemento solo para ir viendo sus variantes, pero es mejor declararlo aqui directamente de esa forma se podrian usar sus variables en el BOX

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
            // se comenta este box para poder ver el NavigationRailEjemplo()
            Box(
                // Los Modifiers cambian el comportamiento o aspecto de un componente
                modifier = Modifier
                    .fillMaxSize() // Hace que el Box ocupe todoo el espacio disponible en la pantalla
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
    fun ScaffoldScreenNavVertical() {
        // Scaffold provee la estructura básica de Material Design (ideal para barras, botones flotantes, etc.)
        Scaffold(
            // barra de navegacion superior, incluye el btn hamburguesa, titulo de la app y btn de busqueda.
            topBar = {
                CenterAlignedTopBarEjemplo() // título centrado
            },

            floatingActionButton = {
                // botones flotantes, ejemplos
                ExtendedFloatingActionButtonEjemplo() //- con texto e icono
            },
            // Define el color de fondo usando la paleta de colores del tema actual del sistema
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { paddingValues ->
            /*content: El bloque de código (lambda) donde va el contenido principal de la pantalla,
            *   el cual recibe un parámetro 'paddingValues' obligatorio para respetar los márgenes.*/

            NavigationRailEjemplo(paddingValues) // ejemplo de navegacion extendida

        }
    }

    /**
     * COMPONENTE PRINCIPAL: TopAppBar (Material Design 3)
     *
     * Este componente representa la barra superior estándar de la aplicación. Su característica
     * principal es que el título se alinea automáticamente a la izquierda (o derecha en sistemas RTL),
     * justo al lado del ícono de navegación.
     *
     * RECOMENDACIÓN DE USO:
     * Es ideal para las pantallas principales de la aplicación, secciones densas en datos o niveles
     * intermedios de navegación donde se requiere un aspecto clásico, limpio y con espacio suficiente
     * para títulos largos.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - title: Ranura (slot) para el componente de texto principal que identifica a la pantalla.
     * - navigationIcon: Ranura izquierda reservada para el ícono de control global (como un menú hamburguesa o flecha de volver atrás).
     * - actions: Bloque de tipo fila en el extremo derecho diseñado para albergar múltiples 'IconButton' de acciones secundarias.
     * - colors: Contenedor de estilos provisto por 'TopAppBarDefaults' para mapear y homogeneizar los colores de fondo, textos e íconos.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarEjemplo() {
        TopAppBar(
            title = {
                // Título principal de la pantalla, alineado a la izquierda por defecto
                Text(text = "Mi aplicación")
            },
            navigationIcon = {
                // Botón de control de la izquierda: Abre el menú lateral de la app
                IconButton(onClick = { /* Acción del menú lateral */ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú" // Texto de lectura esencial para accesibilidad (TalkBack)
                    )
                }
            },
            actions = {
                // Acción contextual derecha: Abre la barra o pantalla de búsqueda
                IconButton(onClick = { /* Acción de búsqueda */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }
            },
            // Personalización cromática unificada utilizando el esquema de colores oficial de Material 3
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,      // Fondo de la barra con el color de énfasis
                titleContentColor = MaterialTheme.colorScheme.onPrimary, // Color del texto con contraste seguro sobre el fondo
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary, // Color sintonizado para el ícono izquierdo
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary      // Color sintonizado para los íconos de la derecha
            )
        )
    }

    /**
     * COMPONENTE PRINCIPAL: CenterAlignedTopAppBar (Material Design 3)
     *
     * Es una variante especializada de la barra superior donde el título se encuentra perfectamente centrado
     * en el eje horizontal, independientemente de cuántos íconos se coloquen a sus extremos.
     *
     * RECOMENDACIÓN DE USO:
     * Se utiliza principalmente en pantallas de destino único (por ejemplo: la vista de un perfil, una pantalla
     * de ajustes detallada o el carrito de compras). Transmite una sensación de balance, formalidad y alta jerarquía visual.
     *
     * DIFERENCIA CLAVE CON TOPAPPBAR:
     * Restringe un poco más el espacio disponible para el texto del título (ya que puede colisionar con los íconos
     * de ambos lados si el texto es excesivamente largo), pero ofrece un diseño más simétrico y estético.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CenterAlignedTopBarEjemplo() {
        CenterAlignedTopAppBar(
            title = {
                // Título de la aplicación que se mantendrá forzosamente centrado de forma horizontal
                Text(text = "Mi aplicación")
            },
            navigationIcon = {
                // Iconografía izquierda de control: Menú hamburguesa estándar
                IconButton(onClick = { /* Acción del menú lateral */ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú"
                    )
                }
            },
            actions = {
                // Primera acción de la derecha: Acceso directo al panel de notificaciones
                IconButton(onClick = { /* Acción de notificaciones */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones"
                    )
                }
                // Segunda acción de la derecha: Acceso directo a las configuraciones de la sección
                IconButton(onClick = { /* Acción de configuración */ }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración"
                    )
                }
            },
            // Mapeo simétrico de colores idéntico a la barra superior estándar para mantener la consistencia visual
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }


    /**
     * COMPONENTE PRINCIPAL: BottomAppBar (Material Design 3)
     *
     * Este componente actúa como un contenedor horizontal ubicado en la parte inferior de la pantalla.
     * A diferencia de 'NavigationBar' (que se usa estrictamente para cambiar de pantallas completas), la
     * 'BottomAppBar' está destinada a albergar acciones contextuales de la pantalla actual (como herramientas
     * de edición, filtros o accesos rápidos) y, opcionalmente, un botón flotante principal (FAB).
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - actions: Ranura (slot) para colocar múltiples controles de tipo 'IconButton'. Almacena acciones secundarias.
     * - tonalElevation: Aplica una sutil elevación tonal con un tinte cromático para darle profundidad visual.
     * - windowInsets: Configura los márgenes seguros con respecto al sistema operativo. Al usar 'WindowInsets.navigationBars',
     *   garantiza que el contenido se renderice por encima de los botones nativos del sistema o la barra de gestos de Android.
     * - containerColor / contentColor: Gestionan los colores de fondo y de primer plano para íconos/textos.
     */
    @Composable
    fun BottomAppBarEjemplo() {
        BottomAppBar(
            actions = {
                // Primer acceso rápido de la barra de acciones: Inicio
                IconButton(onClick = { /* Acción de inicio */ }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio" // Crucial para la accesibilidad (lectores de pantalla)
                    )
                }

                // Segundo acceso rápido de la barra de acciones: Buscar
                IconButton(onClick = { /* Acción de búsqueda */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                }

                // Tercer acceso rápido de la barra de acciones: Favoritos
                IconButton(onClick = { /* Acción de favoritos */ }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favoritos"
                    )
                }
            },
            tonalElevation = 6.dp, // Genera una sombra tonal más pronunciada que el NavigationBar estándar
            windowInsets = WindowInsets.navigationBars, // Evita colisiones visuales con la interfaz del sistema operativo
            containerColor = MaterialTheme.colorScheme.surface, // Sigue la estética de la superficie general de la app
            contentColor = MaterialTheme.colorScheme.onSurface  // Color contrastante para los íconos internos
        )
    }

    /**
     * COMPONENTE PRINCIPAL: FloatingActionButton (Material Design 3)
     *
     * Este es el Botón de Acción Flotante estándar e icónico de Android. Su tamaño tradicional es de 56x56 dp.
     * Se utiliza para representar la acción afirmativa o de creación más importante en una pantalla determinada.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - onClick: Lambda que ejecuta la lógica principal tras el clic del usuario.
     * - shape: Define la silueta. 'CircleShape' le otorga un aspecto completamente redondo.
     * - elevation: Configura sombras independientes para el estado estacionario y el estado de presión.
     */
    @Composable
    fun FloatingActionButtonEjemplo() {
        FloatingActionButton(
            onClick = { /* Acción que se ejecuta al presionar el botón */ },
            containerColor = MaterialTheme.colorScheme.primary, // Color de énfasis para llamar la atención del usuario
            contentColor = MaterialTheme.colorScheme.onPrimary,   // Color adaptado para que el ícono central resalte de forma legible
            shape = CircleShape, // Modifica el contenedor para que sea perfectamente circular
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp, // Altura visual estándar cuando el botón está en reposo
                pressedElevation = 12.dp // Incrementa la sombra al presionarse para dar una sensación física de hundimiento
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Ícono de suma para denotar creación de elementos
                contentDescription = "Agregar"   // Etiqueta informativa para la accesibilidad
            )
        }
    }

    /**
     * COMPONENTE PRINCIPAL: SmallFloatingActionButton (Material Design 3)
     *
     * Es la variante de menor escala de la familia FAB, con unas dimensiones reducidas de 40x40 dp.
     * Se utiliza de forma complementaria para acciones de menor jerarquía o cuando el espacio en pantalla
     * es sumamente limitado y un FAB estándar resultaría demasiado invasivo.
     *
     * CASOS DE USO RECOMENDADOS:
     * - Acciones secundarias agrupadas cerca de un FAB principal.
     * - Pantallas densas en datos donde no se quiere obstruir la lectura de información.
     */
    @Composable
    fun SmallFloatingActionButtonEjemplo() {
        SmallFloatingActionButton(
            onClick = { /* Acción que se ejecuta al presionar el botón */ },
            containerColor = MaterialTheme.colorScheme.primary, // Mantiene la identidad de color de los botones principales
            contentColor = MaterialTheme.colorScheme.onPrimary,   // Garantiza un contraste óptimo en el ícono pequeño
            shape = CircleShape, // Silueta circular idéntica a sus variantes mayores
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp, // Sombra base en estado pasivo
                pressedElevation = 12.dp // Sombra al interactuar directamente mediante pulsación táctil
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Ícono central adaptado de forma automática a las proporciones del contenedor chico
                contentDescription = "Agregar"   // Texto de soporte para herramientas de accesibilidad
            )
        }
    }


    /**
     * COMPONENTE PRINCIPAL: LargeFloatingActionButton (Material Design 3)
     *
     * Este componente proporciona una variante sobredimensionada del botón de acción flotante estándar.
     * Su tamaño predeterminado es de 96x96 dp (en comparación con los 56x56 dp del FAB normal), lo que lo
     * convierte en el elemento visual con mayor jerarquía de la pantalla.
     *
     * CASOS DE USO RECOMENDADOS:
     * - Dispositivos de gran formato (tabletas o pantallas plegables) donde un FAB común se ve muy pequeño.
     * - Acciones críticas que requieren una accesibilidad táctil impecable o un énfasis extremo.
     * - Superficies de diseño despejadas donde se busca que la acción principal resalte a primera vista.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - onClick: Lambda de código obligatoria que gestiona la acción inmediata tras la pulsación del usuario.
     * - containerColor: Color de fondo del botón, vinculado comúnmente al esquema 'primary' para llamar la atención.
     * - contentColor: Color de contraste diseñado para pintar el ícono interior de manera legible.
     * - shape: Define la geometría estructural. Al usar 'CircleShape', el botón adopta una figura perfectamente circular.
     * - elevation: Administra la proyección de sombras físicas tridimensionales (Z-axis). Permite cambiar dinámicamente
     *   la altura visual del botón en estados de reposo o interacción (presión).
     * - content: Ranura (slot) que recibe el componente gráfico que se ubicará en el centro del botón (comúnmente un 'Icon').
     */
    @Composable
    fun LargeFloatingActionButtonEjemplo() {

        // CONFIGURACIÓN DEL BOTÓN FLOTANTE EN FORMATO GRANDE
        LargeFloatingActionButton(
            onClick = {
                /* Código asíncrono o lógica de negocio que se ejecutará inmediatamente al presionar el botón */
            },
            containerColor = MaterialTheme.colorScheme.primary, // Asigna el color de énfasis principal del tema actual
            contentColor = MaterialTheme.colorScheme.onPrimary,   // Asegura el color de contraste correcto sobre el fondo primario
            shape = CircleShape, // Fuerza al contenedor a mantener una silueta completamente redonda

            // Configuración matemática de sombras basada en elevaciones nativas de Material 3
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp, // Elevación y sombra en estado estático o de reposo
                pressedElevation = 12.dp // Incrementa la sombra simulando que el botón se "eleva" al ser presionado
            )
        ) {
            // ÍCONO CENTRAL DEL BOTÓN
            // En los FAB Grandes, el tamaño interno del ícono también se escala de forma automática para mantener la proporción
            Icon(
                imageVector = Icons.Default.Add, // Clásico ícono de cruz/suma para denotar adición
                contentDescription = "Agregar"   // Etiqueta crucial de accesibilidad para lectores de pantalla (TalkBack)
            )
        }
    }


    /**
     * COMPONENTE PRINCIPAL: ExtendedFloatingActionButton (Material Design 3)
     *
     * Este componente representa una evolución del botón de acción flotante (FAB) tradicional. Se utiliza
     * para enfatizar la acción más importante, principal o más frecuente de una pantalla (por ejemplo:
     * "Crear correo", "Enviar mensaje", "Agregar contacto").
     *
     * CARACTERÍSTICA DISTINTIVA (Comportamiento Expandible):
     * A diferencia de un FAB estándar (que es estrictamente cuadrado o circular con un ícono), el EFAB
     * acepta un parámetro booleano llamado 'expanded'. Cuando está en 'true', despliega tanto el ícono
     * como un texto explicativo a su lado. Cuando pasa a 'false', ejecuta una animación nativa y suave
     * para encogerse, ocultando el texto y transformándose en un FAB compacto.
     * En aplicaciones del mundo real, se suele conectar este estado al scroll de una lista (LazyColumn)
     * para que el botón se contraiga automáticamente mientras el usuario baja y se expanda cuando se detiene.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - onClick: Lambda obligatoria que se ejecuta inmediatamente al presionar el botón.
     * - icon: Contenedor para el elemento gráfico secundario (generalmente un componente 'Icon').
     * - text: Contenedor para la etiqueta de texto que describe textualmente la acción.
     * - expanded: Estado booleano dinámico que dicta si el texto debe ser visible o si el botón se encoge.
     * - containerColor / contentColor: Gestionan los esquemas de color de fondo y de primer plano del botón.
     * - shape: Modifica la geometría exterior. Material 3 recomienda por defecto esquinas redondeadas.
     * - elevation: Controla la sombra proyectada (eje Z). Permite configurar elevaciones independientes
     *   para diferentes estados mecánicos del botón como reposo, presión, foco o arrastre.
     */
    @Composable
    fun ExtendedFloatingActionButtonEjemplo() {

        // 1. GESTIÓN DEL ESTADO DE EXPANSIÓN
        // Este booleano recuerda si el botón debe mostrar su texto completo (true) o solo el ícono (false)
        var isExpanded by remember { mutableStateOf(true) }

        // 2. CONFIGURACIÓN DEL BOTÓN FLOTANTE EXTENDIDO
        ExtendedFloatingActionButton(
            onClick = {
                println("EFAB presionado.") // Registro técnico en consola para verificar la acción del clic
                isExpanded = !isExpanded   // Invierte de forma inmediata el estado, alternando entre expandido y contraído
            },
            icon = {
                // Ícono de envío adaptado automáticamente para sistemas de lectura de derecha a izquierda (RTL)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Enviar"
                )
            },
            text = {
                // Texto descriptivo que acompaña al ícono cuando el botón está expandido
                Text(text = "Enviar mensaje")
            },
            expanded = isExpanded, // Vincula directamente la animación mecánica del componente al estado booleano
            containerColor = MaterialTheme.colorScheme.primary, // Aplica el color principal configurado en el tema de la app
            contentColor = MaterialTheme.colorScheme.onPrimary,   // Garantiza un color de contraste adecuado para el texto e ícono
            shape = RoundedCornerShape(16.dp), // Define esquinas suavizadas con un radio personalizado de 16 píxeles de densidad

            // Ajuste milimétrico de las elevaciones y sombras usando los valores predeterminados de Material 3
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp, // Sombra sutil en estado de reposo
                pressedElevation = 12.dp // Sombra más profunda e intensa mientras el usuario mantiene pulsado el botón
            )
        )
    }


    /**
     * COMPONENTE PRINCIPAL: NavigationBar (Material Design 3)
     *
     * Este componente implementa la clásica barra de navegación inferior (Bottom Navigation). Es el patrón
     * de diseño recomendado en Android para la navegación principal en pantallas compactas (teléfonos móviles).
     * Permite al usuario alternar de forma rápida y cómoda entre un rango de 3 a 5 destinos clave.
     *
     * UBICACIÓN ESTRUCTURAL EN COMPOSE:
     * Tal como se menciona en la nota del código, para que este componente sea verdaderamente funcional,
     * su lógica de estado ('selectedItem') y la estructura del 'NavigationBar' deben declararse un nivel arriba,
     * idealmente pasándolo al parámetro 'bottomBar = { NavigationBarEjemplo() }' dentro de un 'Scaffold'.
     * Esto permite que el contenedor del contenido principal (como un Box o un NavHost) conozca qué pestaña
     * está activa y actualice la pantalla en consecuencia (patrón de elevación de estado o State Hoisting).
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - containerColor: Color de fondo de la barra. Sigue las directrices de Material 3 usando el color 'surface'.
     * - contentColor: Color aplicado por defecto a los íconos y textos internos.
     * - tonalElevation: Añade una sutil elevación tonal (efecto visual que oscurece o aclara el contenedor según
     *   el color primario de la app) para separarlo visualmente del fondo del contenido.
     * - windowInsets: Controla los márgenes automáticos del sistema. Asegura que la barra respete la "zona segura"
     *   inferior del teléfono (como la barra de gestos o botones nativos de Android) para que no se superpongan.
     *
     * COMPONENTES DE APOYO INTERNOS:
     * - NavigationBarItem: El botón individual para cada pestaña. Gestiona automáticamente las animaciones de
     *   selección de Material 3 (un óvalo de color de fondo que rodea al ícono seleccionado).
     * - alwaysShowLabel: Si está en 'true', el texto siempre es visible. Si está en 'false', el texto se oculta
     *   y solo aparece de forma animada cuando la pestaña es seleccionada por el usuario.
     */
    @Composable
    fun NavigationBarEjemplo() {

        // 1. DEFINICIÓN DE DATOS Y ESTADOS
        // Listas con los textos y los íconos para cada una de las pestañas inferiores
        val items = listOf("Inicio", "Buscar", "Perfil")
        val icons = listOf(
            Icons.Default.Home,
            Icons.Default.Search,
            Icons.Default.Person
        )

        // Al usar 'rememberSaveable', la pestaña seleccionada no se reinicia a 0 si el usuario
        // rota la pantalla o si el sistema destruye temporalmente la actividad por falta de memoria
        var selectedItem by rememberSaveable { mutableStateOf(0) }

        // 2. CONTENEDOR DE LA BARRA INFERIOR
        NavigationBar(
            modifier = Modifier.fillMaxWidth(), // Se extiende horizontalmente por completo en la pantalla
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            tonalElevation = 3.dp, // Aplica el tinte de elevación estándar de Material 3
            windowInsets = NavigationBarDefaults.windowInsets // Evita colisiones con la barra de navegación del sistema Android
        ) {

            // 3. CONSTRUCCIÓN DINÁMICA DE LAS PESTAÑAS
            // Recorremos los ítems para generar un botón independiente por cada uno de ellos
            items.forEachIndexed { index, label ->
                NavigationBarItem(
                    label = { Text(text = label) },
                    selected = selectedItem == index, // Verifica si el índice actual coincide con el estado seleccionado
                    onClick = {
                        selectedItem = index // Actualiza el estado al hacer clic, provocando la recomposición visual
                    },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = label
                        )
                    },
                    alwaysShowLabel = true // Mantiene el texto de la pestaña visible en todo momento, esté seleccionada o no
                )
            }
        }
    }


    /**
     * COMPONENTE PRINCIPAL: NavigationRail (Material Design 3)
     *
     * Este componente proporciona una barra de navegación vertical fija en el lateral de la pantalla (similar a un 'aside' en entornos web).
     * Está diseñado específicamente para optimizar la ergonomía y usabilidad en dispositivos con pantallas anchas, tales como:
     * - Tabletas en modo horizontal (Landscape).
     * - Dispositivos plegables (Foldables).
     * - Aplicaciones de escritorio o Chromebooks.
     *
     * A diferencia del 'NavigationBar' (que se ubica abajo en vertical) o del 'ModalNavigationDrawer' (que se superpone ocultando la pantalla),
     * el 'NavigationRail' convive de forma paralela con el contenido principal. Por lo tanto, se declara dentro del cuerpo de la interfaz
     * (usualmente dentro de un contenedor horizontal como un 'Row') y no en ranuras reservadas del Scaffold.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - header: Lambda opcional ubicada en la parte superior de la barra. Ideal para colocar logotipos, avatares de usuario o un botón flotante (FAB).
     * - containerColor: Define el color de fondo de la barra vertical.
     * - contentColor: Define el color por defecto de los elementos que aloja.
     * - content: Ranura (slot) que recibe una serie de componentes de tipo 'NavigationRailItem'.
     *
     * COMPONENTES DE APOYO INTERNOS:
     * - NavigationRailItem: El botón individual para cada destino de navegación. Incluye estados visuales nativos para selección, deshabilitación e indicadores de forma.
     * - alwaysShowLabel: Parámetro de configuración crítico. Si se define en 'false', la etiqueta de texto se oculta automáticamente y solo emerge de forma animada cuando el ítem pasa a estar seleccionado.
     */
    @Composable
    fun NavigationRailEjemplo(padding: PaddingValues) {

        // 1. DEFINICIÓN DE DATOS Y ESTADOS
        // Listas de texto e íconos que representarán las opciones del menú lateral
        val items = listOf("Inicio", "Buscar", "Perfil", "Favoritos", "Ajustes")
        val icons = listOf(
            Icons.Default.Home,
            Icons.Default.Search,
            Icons.Default.Person,
            Icons.Default.Favorite,
            Icons.Default.Settings
        )

        // 'rememberSaveable' es superior a 'remember' aquí: conserva la pestaña seleccionada
        // incluso si el usuario rota la pantalla del dispositivo (cambio de configuración)
        var selectedItem by rememberSaveable { mutableStateOf(0) }

        // 2. DISPOSICIÓN HORIZONTAL (Contenedor Raíz)
        // Usamos un Row para colocar la barra de navegación a la izquierda y el contenido a la derecha
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // Respeta los márgenes seguros provistos por el Scaffold padre
        ) {

            // 3. LA BARRA DE NAVEGACIÓN VERTICAL
            NavigationRail(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                header = {
                    // Componente visual superior: Imagen del perfil del usuario
                    Image(
                        painter = painterResource(id = R.drawable.avatar), // Requiere un archivo 'avatar.png/xml' en res/drawable
                        contentDescription = "Avatar de usuario",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(48.dp)
                            .clip(CircleShape) // Recorta la imagen de forma perfectamente circular
                    )
                }
            ) {
                // Ciclo indexado para renderizar dinámicamente cada botón del menú lateral
                items.forEachIndexed { index, label ->
                    NavigationRailItem(
                        selected = selectedItem == index, // Si coincide el índice, se activa el indicador visual de foco
                        onClick = { selectedItem = index }, // Modifica el estado al pulsar, forzando la recomposición
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = label
                            )
                        },
                        label = { Text(text = label) },
                        alwaysShowLabel = false, // Oculta el texto si el ítem no está seleccionado; da un aspecto más limpio
                        enabled = true // El botón está activo y responde a las interacciones
                    )
                }
            }

            // 4. ÁREA DE CONTENIDO DINÁMICO
            // Este Box ocupa el espacio restante de la derecha y muta según el ítem seleccionado
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center // Centra el texto tanto horizontal como verticalmente
            ) {
                Text(
                    text = "Pantalla ${items[selectedItem]}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }


    /**
     * COMPONENTE PRINCIPAL: ModalNavigationDrawer (Material Design 3)
     *
     * Este componente actúa como el contenedor raíz de la pantalla cuando se requiere un menú lateral
     * desplegable. Se posiciona por fuera del 'Scaffold' debido a que debe superponerse a toda la interfaz
     * gráfica (incluyendo la TopAppBar y la barra de navegación del sistema) cuando se activa.
     *
     * PROPIEDADES Y PARÁMETROS CLAVE:
     * - drawerContent: Lambda que define qué se va a renderizar dentro del panel deslizante.
     *   Generalmente aloja un 'ModalDrawerSheet' para mantener la estética estándar de Material 3.
     * - drawerState: Controla el estado mecánico del menú (Abierto/Cerrado). Se inicializa con 'rememberDrawerState()'.
     * - gesturesEnabled: Booleano que activa o desactiva la capacidad del usuario para arrastrar el menú
     *   con el dedo desde el borde de la pantalla.
     * - content: El contenido principal de la pantalla que se verá "debajo" del drawer (comúnmente un Scaffold).
     *
     * MÉTODOS CLAVE (Requieren un CoroutineScope debido a que son funciones suspendidas / animadas):
     * - drawerState.open(): Desplaza el menú lateral hacia adentro de la pantalla para mostrarlo.
     * - drawerState.close(): Oculta el menú lateral deslizándolo hacia afuera de la pantalla.
     * - drawerState.isOpen: Propiedad booleana de lectura para verificar si el menú está visible.
     * - drawerState.isClosed: Propiedad booleana de lectura para verificar si el menú está oculto.
     *
     * COMPONENTES DE APOYO INTERNOS:
     * - ModalDrawerSheet: Aplica contenedores y elevaciones estándar de Material 3 al menú lateral.
     * - NavigationDrawerItem: Botón pre-diseñado y optimizado para representar cada opción dentro del menú.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavigationDrawerEjemplo() {

        // 1. GESTIÓN DE ESTADOS Y CONTROLADORES
        // Mantiene el estado del Drawer (Cerrado por defecto) y sobrevive a las recomposiciones
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        // Necesario para disparar las funciones suspendidas .open() y .close() en respuesta a eventos de clic
        val scope = rememberCoroutineScope()

        // Listas de datos que alimentarán la estructura del menú de manera dinámica
        val drawerItems = listOf("Inicio", "Buscar", "Perfil", "Favoritos", "Ajustes")
        val drawerIcons = listOf(
            Icons.Default.Home,
            Icons.Default.Search,
            Icons.Default.Person,
            Icons.Default.Favorite,
            Icons.Default.Settings
        )

        // Estado que recuerda el índice del ítem actualmente seleccionado para cambiar el contenido de la pantalla
        var selectedItem by remember { mutableIntStateOf(0) }

        // 2. CONTENEDOR RAÍZ DE LA INTERFAZ
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = true, // Permite la interacción mediante gestos táctiles (Swipe)
            drawerContent = {
                // ModalDrawerSheet proporciona el fondo, esquinas redondeadas y ancho estándar del menú M3
                ModalDrawerSheet {

                    // Encabezado del Perfil de Usuario
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Imagen representativa del usuario (Icono genérico estilizado en círculo)
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "foto de perfil",
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        // Texto de bienvenida principal
                        Text(
                            text = "Bienvenido(a)",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Texto secundario con el correo electrónico del usuario
                        Text(
                            text = "usuario@gmail.com",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Línea divisoria sutil para separar el encabezado del bloque de opciones
                    HorizontalDivider()

                    // Etiqueta de sección para el menú
                    Text(
                        text = "Menú",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Construcción dinámica de los botones del menú usando bucles indexados
                    drawerItems.forEachIndexed { index, label ->
                        NavigationDrawerItem(
                            label = { Text(text = label) },
                            selected = selectedItem == index, // Si coincide el índice, cambia su color visual de selección
                            icon = {
                                Icon(
                                    imageVector = drawerIcons[index],
                                    contentDescription = label
                                )
                            },
                            badge = {
                                // Ejemplo condicional: Añade una burbuja de notificación sólo al primer elemento ("Inicio")
                                if (index == 0) {
                                    Badge { Text(text = "3") }
                                }
                            },
                            onClick = {
                                selectedItem = index // Actualiza la pantalla con la opción elegida
                                scope.launch {
                                    drawerState.close() // Cierra el menú lateral de forma animada (Asíncrona)
                                }
                            },
                            // Aplica los márgenes horizontales estándar recomendados para ítems de Drawer
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            shape = RoundedCornerShape(12.dp) // Esquinas redondeadas personalizadas para el botón seleccionado
                        )
                    }
                }
            }
        ) {
            // 3. CUERPO O CONTENIDO DE LA PANTALLA
            // Se renderiza por debajo del Drawer y reacciona a los cambios de 'selectedItem'
            Scaffold(
                topBar = {
                    // Barra superior de la aplicación
                    TopAppBar(
                        title = {
                            Text(
                                text = "Mi aplicacion",
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        navigationIcon = {
                            // El clásico botón de hamburguesa (Tres líneas)
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open() // Despliega el menú de forma animada al pulsar el botón
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menú"
                                )
                            }
                        },
                        // Personalización de colores de la TopAppBar basándose en el tema actual
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface
            ) { paddingValues ->
                // El contenedor principal de nuestra vista de usuario.
                // Se le aplican los 'paddingValues' calculados por el Scaffold para que su contenido
                // se posicione exactamente debajo de la TopAppBar y no se esconda detrás de ella.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    // Muestra dinámicamente un texto con el nombre de la sección seleccionada en el menú lateral
                    Text(text = "seccion: ${drawerItems[selectedItem]}")
                }
            }
        }
    }

    @Composable
    fun TabRowExample() {
        // Lista con los textos que se mostrarán en cada pestaña
        val tabTitles = listOf("Inicio", "Favoritos", "Perfil")

        // Lista con los íconos vectoriales correspondientes a cada pestaña
        val tabIcons = listOf(
            Icons.Default.Home,
            Icons.Default.Favorite,
            Icons.Default.Person
        )

        // Estado que guarda el índice de la pestaña seleccionada actualmente (empieza en 0)
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        // Lista de fuentes de interacción para detectar eventos táctiles de forma independiente en cada pestaña
        val interactionSource = remember { List(tabTitles.size) { MutableInteractionSource() } }

        // Estructura de pantalla Scaffold
        Scaffold(
            topBar = {
                // El contenedor de las pestañas se ubica correctamente en la barra superior
                TabRow(
                    selectedTabIndex = selectedTabIndex, // Le dice al TabRow cuál pestaña resaltar
                    modifier = Modifier.statusBarsPadding(), // Evita que el contenido se superponga con la barra de estado del sistema
                    containerColor = MaterialTheme.colorScheme.surfaceVariant, // Color de fondo del TabRow
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer, // Color por defecto para el contenido interno
                    indicator = { tabPositions ->
                        // Configuración de la línea indicadora que se mueve debajo de la pestaña seleccionada
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndex]) // Mueve el indicador a la pestaña activa
                                .height(3.dp), // Grosor de la línea indicadora
                            color = MaterialTheme.colorScheme.primary // Color de la línea indicadora
                        )
                    },
                    divider = {
                        // Línea horizontal divisoria debajo de todo el TabRow
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant, // Color sutil para el divisor
                            thickness = 1.dp // Grosor de la línea divisoria
                        )
                    }
                ) {
                    // Ciclo para crear cada una de las pestañas basadas en la lista de títulos
                    tabTitles.forEachIndexed { index, title ->
                        val enabled = index != 1 // para deshabilitar la pestaña 1

                        Tab(
                            selected = selectedTabIndex == index, // Evalúa si esta pestaña específica es la seleccionada
                            onClick = {
                                if (enabled) selectedTabIndex = index
                            }, // Cambia el estado al índice actual al hacer clic
                            modifier = Modifier.padding(horizontal = 4.dp), // Margen interno horizontal para espaciado
                            enabled = enabled, // Define que la pestaña está activa y responde a clics
                            text = {
                                // Texto que se muestra dentro de la pestaña
                                Text(text = title, style = MaterialTheme.typography.titleLarge, fontSize = 16.sp)
                            },
                            icon = {
                                // Ícono que acompaña al texto de la pestaña
                                Icon(imageVector = tabIcons[index], contentDescription = title)
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary, // Color del texto/ícono cuando está seleccionada
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant, // Color cuando NO está seleccionada
                            interactionSource = interactionSource[index] // Asigna el detector de interacciones individual
                        )
                    }
                }
            }
        ) { paddingValues ->
            // CUERPO PRINCIPAL DEL SCAFFOLD: Aquí se renderiza el contenido real de las pantallas
            Column(
                modifier = Modifier
                    .fillMaxSize() // Ocupa todo el espacio disponible en la pantalla
                    .padding(paddingValues) // Aplica obligatoriamente el espaciado generado por el Scaffold (evita solapamientos)
                    .padding(16.dp) // Añade un margen interno extra de 16dp para descolar el texto de los bordes
            ) {
                // Evaluamos cuál pestaña está activa y dibujamos el contenido correspondiente
                when (selectedTabIndex) {
                    0 -> Text("Pantalla de inicio", style = MaterialTheme.typography.bodyLarge)
                    1 -> Text("Pantalla de favoritos", style = MaterialTheme.typography.bodyLarge)
                    2 -> Text("Pantalla de perfil", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }


    // ejemplo ScrolleableTabRow


    //EJEMPLO DE JUEGO CON NAVIGATION DRAWER Y SCAFFOLD/

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EjemploJuegoConDrawerApp() {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = true, // El usuario puede arrastrar desde la izquierda siempre
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        text = "Menú del Juego",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text("Volver al Menú Principal") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("bienvenida") {
                                popUpTo("bienvenida") { inclusive = true }
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Ajustes Rápidos") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("configuracion")
                        }
                    )
                }
            }
        ) {
            NavHost(navController = navController, startDestination = "bienvenida") {
                composable("bienvenida") {
                    PantallaBienvenida(
                        onJugarClick = { navController.navigate("juego") },
                        onConfigClick = { navController.navigate("configuracion") }
                    )
                }

                composable("juego") {
                    PantallaTableroJuego(
                        onMenuClick = { scope.launch { drawerState.open() } },
                        onTerminar = { navController.navigate("fin_juego") }
                    )
                }

                composable("fin_juego") {
                    PantallaFinJuego(navController = navController)
                }

                composable("configuracion") {
                    PantallaConfiguracion(onVolver = { navController.popBackStack() })
                }
            }
        }
    }

    /**
     * PANTALLA 1: BIENVENIDA (Menú de Inicio)
     * No hace uso de Scaffold para permitir un diseño visual libre e inmersivo.
     */
    @Composable
    fun PantallaBienvenida(
        onJugarClick: () -> Unit,
        onConfigClick: () -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "🧩 KAKURO MASTER",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Desafía tu mente",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = onJugarClick,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text("NUEVA PARTIDA")
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onConfigClick,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text("CONFIGURACIÓN")
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "(Pista: Desliza desde la izquierda para el menú rápido)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    /**
     * PANTALLA 2: TABLERO DE JUEGO
     * Usa su propio Scaffold local para organizar las barras de la partida.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaTableroJuego(
        onMenuClick: () -> Unit,
        onTerminar: () -> Unit
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Sudoku - Nivel Medio") },
                    navigationIcon = {
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir Menú lateral"
                            )
                        }
                    },
                    actions = {
                        Text(
                            text = "05:24",
                            modifier = Modifier.padding(end = 16.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "[ Aquí se renderizaría la cuadrícula del juego ]",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(onClick = onTerminar) {
                        Text("SIMULAR RESOLVER JUEGO")
                    }
                }
            }
        }
    }

    /**
     * PANTALLA 3: FIN DE JUEGO
     * Diseño libre a pantalla completa para celebrar la victoria o derrota.
     */
    @Composable
    fun PantallaFinJuego(navController: NavController) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "🎉 ¡VICTORIA! 🎉",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Completado con éxito en 05:24",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate("juego") {
                            popUpTo("bienvenida") // Limpia el historial de la partida resuelta
                        }
                    }
                ) {
                    Text("JUGAR DE NUEVO")
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate("bienvenida") {
                            popUpTo("bienvenida") { inclusive = true }
                        }
                    }
                ) {
                    Text("MENÚ PRINCIPAL")
                }
            }
        }
    }

    /**
     * PANTALLA 4: CONFIGURACIÓN
     * Usa un Scaffold propio con título centrado y opciones de interruptores.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaConfiguracion(onVolver: () -> Unit) {
        var sonidoActivo by remember { mutableStateOf(true) }
        var modoOscuro by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Ajustes del Sistema") },
                    navigationIcon = {
                        IconButton(onClick = onVolver) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver atrás"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Preferencias de Audio y Video:",
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Efectos de Sonido")
                    Switch(
                        checked = sonidoActivo,
                        onCheckedChange = { sonidoActivo = it }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Forzar Modo Oscuro")
                    Switch(
                        checked = modoOscuro,
                        onCheckedChange = { modoOscuro = it }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Versión del juego: 1.0.0-Beta (Jetpack Compose)",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }



}

