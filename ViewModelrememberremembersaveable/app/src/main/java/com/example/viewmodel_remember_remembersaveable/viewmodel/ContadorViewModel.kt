package com.example.viewmodel_remember_remembersaveable.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viewmodel_remember_remembersaveable.model.Contador

class ContadorViewModel : ViewModel(){

    var contador by mutableStateOf(Contador()) // Contador() es un objeto por eso se usa mutableStateOf en lugar de mutableIntStateOf.
        private set // hace que solamente pueda ser modificada la variable var contador por el viewmodel.

    fun sumar(){
        // usamos .copy porque la variable valor del objeto Contador es inmutable, es decir es una constante por loc ual no podemos modificarla directamente.
        contador = contador.copy(valor = contador.valor + 1) // incrementamos el valor del objeto en Contador

    }

    fun restar(){
        contador = contador.copy(valor = contador.valor - 1) // decrementa el valor del objeto
    }
}