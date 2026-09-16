package com.example.compartirviewmodel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.compartirviewmodel.model.Contador

class ContadorViewModel: ViewModel(){

    var contador by mutableStateOf(Contador())
        private set // impode que otras clases distintas a viewmodel modifiquen la variable anterior (contador)

    fun incrementar(){
        contador = contador.copy(miValor = contador.miValor + 1)
    }

    fun decrementar(){
        contador = contador.copy(miValor = contador.miValor - 1)
    }
}