package com.example.ciclodevida_viewmodel.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel // permite reutilizar viewmodel
import com.example.ciclodevida_viewmodel.model.Producto

//
class ProductoViewModel : ViewModel(){

    var productos by mutableStateOf<List<Producto>>(emptyList())
        private set // evita que la interface de usuario pueda modificar directamente este valor

    // se ejecuta al cargar el viewmodel
    init {
        cargarProductos()
        Log.d("ProductoViewModel","view model creado.")
    }

    private fun cargarProductos(){
        productos = listOf(
            Producto(1,"laptop hp", 2500.0),
            Producto(2,"impresora epson", 570.0),
            Producto(3,"teclado janus", 200.0),
            Producto(4,"monitor lg", 900.0)
        )
    }

    // se ejecuta automaticamente cuando el viewmodel es destruido
    override fun onCleared() {
        super.onCleared()
        Log.d("ProductoViewModel","View model destruido")
    }
}