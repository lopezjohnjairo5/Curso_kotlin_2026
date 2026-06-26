package com.example.contenedores

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.constraintlayout.compose.ConstraintLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.contenedores.ui.theme.ContenedoresTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContenedoresTheme {

                /* NOTA:  Tanto la columna como la fila, tienen parametros del modifier parecidos, pero no son iguales,
                * los arragement son diferentes y los alignment tambien, tener cuidado con eso
                * arrangement = Disposicion u orden
                * alignement = alineacion
                * */

                //MiColumna() // ejemplo de elementos dentro de una columna, centrados tanto en vertical como horizontal
                //MiFila() // ejemplo de elementos dentro de la fila centrados
                //MiCaja() // ejemplo de elementos dentro de una caja o BOX, aquí se superponen automaticamente, para alinearlos es necesario posicionar cada elemento independientemente.
                //ContenedorConRestricciones() // ejemplo de elementos dentro del contenedor con restricciones, este contenedor es la version moderna del trabajado en XML donde se
                // ubicaban los elementos en pantalla segun otros elementos presentes, este contenedor requiere ser previamente agregando en libs y en gradle(module), ya que no viene por default.


                // estos contenedores, solamente cargan los elementos
                // visibles, a medida que se hace scroll se van cargando
                // los demas, ademas cuentan con scroll automatico integrado

                //MiColumnaPerezosa()
                //MiFilaPerezosa()
                //MiFilaPerezosaUrlIMGs() // este es un ejemplo de como acceder a imagenes de internet, para esto se requiere implementar coil en libs.version y build.gradle(modulo)

                //MiCuadriculaVerticalPerezosa()
                //MiCuadriculaHorizontalPerezosa()

                // NOTA: de los contenedores anteriores el mas recomendado es BOX y el menos recomendado es ConstraintLayout, esto se debe a que:
                // Box permite posicionar con libertad, y Constraint requiere hacer uso de codigo repetitivo para posicionar elementos en la pantalla
                // y ademas obliga a crear variables para las restricciones y Su motor matemático interno es más pesado.

                /* aqui se muestran los ejemplos de algunos componentes de la interface */
                //MiTarjeta()
                //MiTarjetaElevada()
                MiTarjetaConBorde()
            }
        }
    }
}

// COMPONENTES
@Composable
fun Espaciador(
    h: Dp = 30.dp
){
    /*
    * permite separar elementos mediante un espacio en blanco
    * no es visible en si mismo.
    * NOTA: NO SIRVE EN EL INTERIOR DE UNA BOX, esto se debe a que la box superpone los elementos
    * como si estubieran con position:absolute (simil con css).
    * */
    Spacer(
        modifier = Modifier.height(h)
    )
}

@Composable
fun CargaCircular(
    size: Dp = 60.dp
){
    /*
    * muestra una barra de carga circular,
    * puede ser infinita o no
    * */
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        strokeWidth = 6.dp,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun BarraCarga(){
    /*
    * muestra una barra de carga,
    * puede ser infinita o no
    * */
    LinearProgressIndicator(
        //modifier = Modifier.fillMaxWidth(),
        modifier = Modifier
            .width(150.dp)
            .background(MaterialTheme.colorScheme.secondary),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun MiCanvas(){
    /*
    * creamos un lienzo en blanco
    * para dibujar sobre él,
    * en el caso de este ejemplo un circulo
    * */
    val backgroundColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .size(300.dp) // esta medida se usa en radius al dibujar
            .padding(15.dp)
    ) {
        // dibujando un circulo
        drawCircle(
            color = backgroundColor,
            radius = size.minDimension/4, // tamaño definido en el canvas dividido en 4
        )

    }
}

// CONTENEDORES

@Composable
fun MiColumna(){
    /*
    * es un contenedor de elementos para alinearlos en forma de columna, es decir, uno sobre otro
    * modifier: permite aplicar modificadores como tamaño, padding o background
    * horizontalAlignment: permite alinear los hijos de la columna horizontalmente dentro de esta, puede ser:
    * - Alignment.Start: alinea a la izquierda,
    * - Alignment.CenterHorizontally: alinea al centro,
    * - Alignment.End: alinea a la derecha
    * verticalArrangement: controla el espaciado y distribución vertical de los hijos, puede ser:
    * - Arrangement.Top: Alinea arriba,
    * - Arrangement.Center: centro vertical,
    * - Arrangement.Bottom: alinea abajo,
    * - Arrangement.SpaceBetween: da un espacio igual ENTRE elementos dejando el primero arriba y el ultimo abajo
    * - Arrangement.SpaceAround: distribuye el espacio equitativamente alrededor de todos los elementos hijos,
    * - Arrangement.SpaceEvenly: distribuye el espacio sobrante de manera igualitaria entre los elementos, incluyendo el principio y el final de la columna,
    * content: bloque donde se definen los @Composable hijos que tendrá la columna
    * */

    Column(
        // aqui parametros
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // aquí los hijos de la columna
        // Tu contenido principal va aquí (Textos, Listas, Imágenes, etc.)
        CargaCircular()
        Espaciador()
        BarraCarga()
        MiCanvas()
    }
}


@Composable
fun MiFila(){
    /*
    * Es un contenedor de elementos, el cual permite alinearlos
    * uno al lado del otro, es decir en fila
    * modifier: permite aplicar modificadores para la apariencia o comportamiento de la fila
    * horizontalArrangement: distribuye los elementos en la fila, ej:
    *   - Arragement.Start: alinear elementos a la izquierda
    *   - Arragement.Center: centra los elementos en la fila
    *   - Arragement.End: alinea los elementos hacia la derecha
    *   - Arragement.SpaceBetween: pone un espacio entre los elementos, NO entre elemento y bordes de fila
    *   - Arragement.SpaceAround: pone un espacio uniforme alrededor de los elementos, es mas pequeño entre los limites de la fila y el primer o ultimo elemento
    *   - Arragement.SpaceEvenly: pone un espacio uniforme alrededor de los elementos
    *   - Arragement.Absolute: permite definir posiciones absolutas para los elementos dentro de la fila.
    * verticalAlignment: alinea los hijos o elementos de la fila, ej:
    *   - Alignment.Top:
    *   - Alignment.Bottom:
    *   - Alignment.CenterVertically:
    * */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // opcional
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ){
        // aquí los hijos de la fila
        // Tu contenido principal va aquí (Textos, Listas, Imágenes, etc.)
        CargaCircular()
        Espaciador()
        BarraCarga()
        MiCanvas()
    }
}

@Composable
fun MiCaja(){
    /*
    * Es un contenedor de elementos, utilizado
    * para organizar los elementos en la
    * interface de usuario
    * Este contenedor es util cuando se desee suporpocionar elementos, es decir
    * los elementos puestos en este contenedor se
    * dibujan en el orden declarado y se posicionan uno
    * sobre otro. Algunos de sus parametros son:
    * - contentAlignment
    * -
    * cada hijo de la Box puede ser alineado de manera independiente mediante Modifier.align()
    * modifier: permite aplicar modificadores para la apariencia o comportamiento
    * */
    Box(
        modifier = Modifier
            .fillMaxSize() // ocupará toda la pantalla
    ){
        Image(
            painter = painterResource(id = R.drawable.img1),
            contentDescription = "Imagen de fondo cel", // necesaria para accesibilidad
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = "Curso de Jetpack Compose",
            style = TextStyle(color = Color.Black, fontSize = 25.sp),
            modifier = Modifier.align(Alignment.Center)
        )

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Presione")
        }
    }
}

@Composable
fun ContenedorConRestricciones(){
    /*
    * Contenedor avanzado, permite organizar elementos en la pantalla,
    * mediante una serie de restricciones (constraint), estas determinan como los elementos
    * se posicionan y dimensionan en relacion con
    * otros componentes y con el contenedor padre
    * Util para diseños complejos, porque permite replicar estructuras similares
    * a las utilizadas anteriormente en XML.
    *
    * createRefs = su proposito es crear referencias unicas para cada componente dentro del ConstraintLayout
    *
    * Parametros y metodos de constraint layout:
    * - top.linkTo : Conecta la parte superior de un componente a la parte superior o inferior de otro componente
    * - start.linkTo : Conecta la parte lateral izquierda de un componente a la parte lateral izquierda o derecha de otro componente
    * - end.linkTo : Conecta la parte lateral derecha de un componente a la parte lateral izquierda o derecha de otro componente
    * - bottom.linkTo : Conecta la parte inferior de un componente a la parte superior o inferior de otro componente
    *
    *  Targets
    * - parent.* : permite conectar con los bordes del elemento padre o contenedor, el Comodin(*) puede ser top, bottom, start, end
    * - elemento.* : permite conectar con los bordes de un elemento en este caso distinto al padre, el Comodin(*) puede ser top, bottom, start, end
    *
    *  Dimensiones
    * - Dimension.wrapContent = El tamaño del componente se ajusta automaticamente al contenido
    * - Dimension.fillToConstraints = Hace que el componente ocupe todoo el espacio disponible entre las restricciones definidas
    * - Dimension.preferredWrapContent = Ajusta el tamaño del componente al contenido, pero permite que se expanda si las restricciones lo requieren.
    * - Dimension.value(dp) = Asigna un tamaño fijo en dp al componente
    *
    *  Modifier
    * - padding = margen interno del layout en dp o sp
    * - fillMaxWidth / fillMaxHeight = para ocupar todoo el ancho o alto disponible
    *
    * NOTA: Para utilizar este contenedor en JetpackCompose es necesario agregar en libs y build.gradle (modulo)
    * esto se debe a que Google diseñó Jetpack Compose bajo un principio de modularidad extrema. En lugar de
    * incluir todoo en un solo paquete gigante, el sistema está dividido en piezas independientes que se descargan solo si las necesitas.
    *
    * */

    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // 1. CREAR LAS REFERENCIAS: Se generan usando createRefs()
        val (miImagen, textoTitulo, textoDescripcion, botonGuardar) = createRefs() // Creacion de variables que representan los componentes visuales.

        Image(
            painter = painterResource(id=R.drawable.img),
            contentDescription = "Imagen de prueba",
            modifier = Modifier
                .size(100.dp)
                .constrainAs(miImagen){ // este es el nombre que hace uso este elemento para las restricciones
                    top.linkTo(parent.top) // estas son las restricciones, en la parte superior se alinea con la parte superior del contenedor
                    start.linkTo(parent.start) // aqui se alinea la parte Izquierda con la parte izquierda del contenedor
                },
            contentScale = ContentScale.Crop // esto acerca la imagen y corta para que solo sea visible lo que quepa en el contenedor de la imagen.
        )
        Text(
            text = "¡Hola Kotlin!",
            modifier = Modifier.constrainAs(textoTitulo) { // 2. ASIGNAR LA REFERENCIA
                // 3. DEFINIR LAS REGLAS (CONSTRAINTS)
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(miImagen.end, margin = 16.dp)
            }
        )

        Text(
            text = "Este es un ejemplo de uso de Constraint Layout.",
            modifier = Modifier.constrainAs(textoDescripcion) { // 2. ASIGNAR LA REFERENCIA
                // 3. DEFINIR LAS REGLAS (CONSTRAINTS)
                top.linkTo(textoTitulo.bottom, margin = 16.dp)
                start.linkTo(miImagen.end, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp) // limita el tamaño del contenedor del texto, diciendole hasta donde se puede extender, sin esta linea el texto sigue de largo y se pierde.
                width = Dimension.fillToConstraints // asignamos todoo el tamaño disponible
            }
        )

        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.constrainAs(botonGuardar) { // 2. ASIGNAR LA REFERENCIA
                // 3. DEFINIR LAS REGLAS (Enlazado al texto de arriba)
                top.linkTo(textoDescripcion.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            }
        ) {
            Text("Guardar")
        }
    }
}

@Composable
fun MiColumnaPerezosa(){
    /*
    * este contenedor solamente cargará en memoria
    * los elementos que se encuentren visibles,
    * a medida que se desplaza por la pantalla se irán
    * volviendo visibles los demas elementos,
    * esto ayuda al rendimiento.
    * items -item: son funciones propias de LazyColumn, permiten recorrer un listado de elementos
    * propios de una lista.
    * items: permite crear listas dinámicas de elementos para componentes como LazyColumn o LazyRow. Utilizado para iterar sobre datos y renderizar multiples elementos en una columna o fila de forma eficiente.
    * item: permite poner un unico elemento.
    * Se puede hacer uso de varias listas y varios elementos dentro de la lazycolumn
    * */

    // constante tipo lista que tendrá 100 elementos
    val itemsList = List(100){"elemento N° $it"}
    val itemsList2 = List(20){"Hola N° $it"}

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        // iteramos sobre la lista de elementos y los mostramos
        items(itemsList){ item ->
            Text(text = item, fontSize = 20.sp)
        }

        items(itemsList2){ item ->
            Text(
                text = item,
                fontSize = 30.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        item{
            Text(
                text = "Unico elemento",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun MiFilaPerezosa(){
    /*
    * similar a la columna perezosa, pero en fila
    *
    * */
    val itemsLanguages = listOf("Cobol","fortran","Ada","Perl","Ruby","Python", "Js", "Java", "PHP", "c", "c++")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsLanguages) { language ->

            Text(
                text = language,
                modifier = Modifier
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}


@Composable
fun MiFilaPerezosaUrlIMGs(){
    /*
    * Debido a que para acceder a las imagenes
    * se requiere acceder a internet, es necesario
    * usar la lib coil, la cual se debe poner en libs.version y en build.gradle(module),
    * ademas es necesario establecer los permisos de descarga de internet en el manifest
    *
    * */
    val listImgs = listOf(
        "https://cdn.pixabay.com/photo/2015/04/10/01/41/fox-715588_1280.jpg",
        "https://cdn.pixabay.com/photo/2021/04/05/14/54/meerkats-6153748_1280.jpg",
        "https://cdn.pixabay.com/photo/2023/01/10/16/23/hedgehog-7710053_1280.jpg",
        "https://cdn.pixabay.com/photo/2017/07/21/15/07/frog-2525994_1280.jpg",
        "https://cdn.pixabay.com/photo/2021/08/04/18/44/ape-6522381_1280.jpg",
        "https://cdn.pixabay.com/photo/2015/11/06/15/39/cows-1029077_1280.jpg"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // este se pone para poder centrar las imgs visualmente en vertical
            .padding(32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listImgs){ imgUrl->
            // AsyncImage = viene gracias a la lib coil, permite traer imagenes de internet de forma asincrona
            AsyncImage(
                model = imgUrl,
                contentDescription = "imagen de la Url",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(15.dp)), // aplicamos un redondeo a la imagen
                contentScale = ContentScale.Crop // cortamos la imagen
            )
        }
    }
}

@Composable
fun MiCuadriculaVerticalPerezosa(){
    /*
    * Este componente permite mostrar los elementos
    * mediante una cuadricula con desplazamiento en vertical
    * de manera eficiente
    * y optimizada, para esto solamente se cargan los elementos
    * visibles en pantalla (lazy loading)
    * Parametros
    * - GridCells.Fixed(int): fija el # de columnas que tendrá la cuadricula
    * - GridCells.Adaptative(minSize:Dp): determina dinamicamente el # de columnas garantizando que cada una tenga al menos el tamaño minSize.dp definido para el ancho
    * Ideal para mostrar conjuntos de elementos en cuadriculas
    * */

    val listElements = List(20){
        "Elemento ${it+1}" // it es una variable interna de List, indica "si mismo", por lo cual tomará los valores desde el cero 0, hasta el tamaño de la lista -1 en este caso 20-1=19
    }

    LazyVerticalGrid(
        //columns = GridCells.Fixed(2), // maximo 2 columnas
        columns = GridCells.Adaptive(120.dp), // ancho minimo de la celda = 120.dp
        modifier = Modifier.fillMaxSize()
    ){
        items(listElements){ element->
            // aqui se crea el diseño visual de los elementos, es decir como se verá cada columna
            // esta funcion la tenemos que crear
            GridItem(element = element)
        }
    }

}

@Composable
fun GridItem( element: String){
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = "Icono de ejemplo",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado
            Text(text = element, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MiCuadriculaHorizontalPerezosa(){
    /*
    * Este componente permite mostrar los elementos
    * mediante una cuadricula con desplazamiento en horizontal
    * de manera eficiente
    * y optimizada, para esto solamente se cargan los elementos
    * visibles en pantalla (lazy loading)
    * Parametros
    * - GridCells.Fixed(int): fija el # de filas que tendrá la cuadricula
    * - GridCells.Adaptative(minSize:Dp): determina dinamicamente el # de filas garantizando que cada una tenga al menos el tamaño minSize.dp definido para el ancho
    * Ideal para mostrar conjuntos de elementos en cuadriculas
    * */

    val myElements = List(20){ "Elemento ${it + 1}" }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(myElements) { myElement->
            GridItemHorizontal(element = myElement)
        }
    }
}

@Composable
fun GridItemHorizontal( element: String){
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
            .border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = element,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary)
    }
}


/* componentes de la interface */

@Composable
fun MiTarjeta(){
    /*
    * Las tarjetas o cards, son componentes de la interface de usuario
    * sirven como contenedores de informacion, tienen un diseño elevado
    * y con bordes redondeados, usados para agrupar contenido relacionado
    * de manera visual y atractiva, proporcionan un efecto de profundidad
    * mediante sombra.
    *
    * Variantes:
    * - ElevatedCard: tiene sombra y se eleva sobre el fondo
    * - OutlinedCard: tiene borde en lugar de sombra
    *
    * */

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(4.dp), // sombra para indicar profundidad
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.img1),
                contentDescription = "Imagen de ejemplo en la tarjeta",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(4.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado
            Text(text = "titulo para la tarjeta", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado
            Text(text = "este es el contenido para la tarjeta", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            )
            {
                Text(
                    text = "Ver más"
                )
            }
        }
    }
}

@Composable
fun MiTarjetaElevada(){
    /*
    * Esta es una variacion de Card, las diferencias con respecto a la primera son las siguientes:
    * Diferencias
    * - Card: No tiene sombra por defecto, ElevatedCard: tiene una sombra sutil(1.dp) por defecto
    * - Card: CardDefaults.cardElevation(), ElevatedCard: CardDefaults.elevationCardElevation(),
    *         Estos metodos permiten pesonalizar la elevacion o nivel de sombra de las tarjetas,
    *         son diferentes segun la tarjeta
    * */
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Titulo de la tarjeta",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado

            Text(
                text = "Este es un ejemplo de elevated card en Jetpack Compose",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun MiTarjetaConBorde(){
    /*
    * Esta es una variacion de Card, las diferencias con respecto a la primera son las siguientes:
    * Diferencias
    * - Card: No tiene sombra por defecto, ElevatedCard: tiene una sombra sutil(1.dp) por defecto
    * - Card: CardDefaults.cardElevation(), ElevatedCard: CardDefaults.elevationCardElevation(),
    *         Estos metodos permiten pesonalizar la elevacion o nivel de sombra de las tarjetas,
    *         son diferentes segun la tarjeta
    * */
    OutlinedCard(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Titulo de la tarjeta",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado

            Text(
                text = "Esta card tiene un borde que se usa para resaltar informacion sin sobrecargar la Interface Hecha con Jetpack Compose",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp)) // separador no visible, establece un espacio en blanco del tamaño especificado

            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            )
            {
                Text(
                    text = "Ver más"
                )
            }

        }
    }
}