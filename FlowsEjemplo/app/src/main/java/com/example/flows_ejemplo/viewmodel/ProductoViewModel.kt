package com.example.flows_ejemplo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flows_ejemplo.model.Producto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel(){
    // flows son flujos

    // privado por encapsulamiento, MutableStateFlow es un observable mutable del listado de productos, será usado por val productoFlow
    private val _productoFlow = MutableStateFlow<List<Producto>>(emptyList())

    // productoFlow es publico y será usado por la interface de usuario
    val productoFlow : StateFlow<List<Producto>> = _productoFlow

    init {
        cargarProductos()
    }

    private fun  cargarProductos(){
        // lanzamos una corrutina desde un viewModel.
        viewModelScope.launch {
            delay(2000) // tiempo que se pausará
            _productoFlow.value = listOf(
                Producto(
                    id=1,
                    nombre = "Audifonos",
                    precio = 50.0,
                    imageUrl = "https://www.ktronix.com/medias/6942103112225-001-1400Wx1400H?context=bWFzdGVyfGltYWdlc3wzMzY2NHxpbWFnZS93ZWJwfGFEZGlMMmhqTmk4eE5ETTJOamt6Tnprd056SXpNQzgyT1RReU1UQXpNVEV5TWpJMVh6QXdNVjh4TkRBd1YzZ3hOREF3U0F8YmExNzQ0ZmJhNDllMzAzZWMxZGJlMWYzZWY1ODRkNGM4MGJlN2M4ZjkwZGNjZjg1NjgzNmE0YTQwMDhiYjZmYQ"
                ),
                Producto(
                    id=2,
                    nombre = "Teclado",
                    precio = 70.0,
                    imageUrl = "https://exitocol.vtexassets.com/arquivos/ids/24645292/teclado-multimedia-usb-para-pc-en-espanol-letra-n-kb501.jpg?v=638618365533600000"
                ),
                Producto(
                    id=3,
                    nombre = "Mouse",
                    precio = 15.0,
                    imageUrl = "https://sony.scene7.com/is/image/sonyglobalsolutions/GG25_MSE-G500_Primary_image?\$mediaCarouselSmall\$&fmt=png-alpha"
                ),
                Producto(
                    id=4,
                    nombre = "Monitor",
                    precio = 500.0,
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkgrMX3SZvYoIwCGYu2whJQxhsy0wHnezmN5CHLPB2Gw&s=10"
                )
            )
        }
    }
}