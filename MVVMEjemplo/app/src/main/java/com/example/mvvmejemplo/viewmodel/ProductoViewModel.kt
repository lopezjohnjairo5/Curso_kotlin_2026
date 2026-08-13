package com.example.mvvmejemplo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mvvmejemplo.model.Producto
import kotlin.collections.emptyList

/*
* Es la clase intermediaria
* gestiona el estado, mantiene los datos
* El constructor si se requiere va junto al nombre de la clase como si fueran parametros
* ej: class ProductoViewModel(val nombre: String) // ejemplo de constructor primario
* el constructor secundario
* seria algo así, ej:
* class ProductoViewModel(val marca: String)
*   // Constructor secundario: llama al primario con 'this'
*   constructor(marca: String) : this(marca, "Genérico") {
        println("Secondary constructor called")
    }
  }
  *
  * NOTA: el init se ejecuta siempre despues del constructor primario y antes de los constructores secundarios (una clase puede tener mas de 1 constructor secundario a la vez).
*
* */


class ProductoViewModel: ViewModel(){
    // variable del estado, mutable crea un estado observable, by sirve para simplificar el acceso al valor para no tener que escribir .value
    var productos by mutableStateOf<List<Producto>>(emptyList()) // inicia con una lista vacia
        private  set // indica que solo dentro de la clase se puede modificar productos

    // bloque de inicializacion de kotlin
    // El bloque init: Ejecuta código inmediatamente después de que el constructor primario es llamado.
    init{
        cargarProductos()
    }
    private fun cargarProductos(){

        // creamos el listado de productos con 3 instancias de la clase Producto.
        productos = listOf(
            Producto("1", "Laptop HP", 350.25),
            Producto("2", "Teclado LG", 50.15),
            Producto("3", "Mouse Logitech", 15.76)
        )

    }
}