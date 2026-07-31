package com.example.ejemplomodilarizacion

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    items : List<String>, // lista de etiquetas
    icons : List<ImageVector>, // lista de iconos de cada etiqueta
    selectedItem : Int, // indice del item actualmente seleccionado
    onItemClick : (Int) -> Unit // funcion callbak que tiene un parametro entero y no retorna nada
    ){
    // ModalDrawerSheet proporciona el fondo, esquinas redondeadas y ancho estándar del menú M3
    ModalDrawerSheet {

        DrawerHeader()// importamos una de las partes

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
        items.forEachIndexed { index, label ->
            NavigationDrawerItem(
                label = { Text(text = label) },
                selected = selectedItem == index, // Si coincide el índice, cambia su color visual de selección
                icon = {
                    Icon(
                        imageVector = icons[index],
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
                    onItemClick(index)
                },
                // Aplica los márgenes horizontales estándar recomendados para ítems de Drawer
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                shape = RoundedCornerShape(12.dp) // Esquinas redondeadas personalizadas para el botón seleccionado
            )
        }
    }
}