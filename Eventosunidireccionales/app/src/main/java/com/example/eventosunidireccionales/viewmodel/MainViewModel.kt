package com.example.eventosunidireccionales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    private val _eventoUI = MutableSharedFlow<String>() // NO guarda el valor emitido
    val eventoUI : SharedFlow<String> = _eventoUI

    fun mostrarMensaje(){
        // lanza una corrutina
        viewModelScope.launch {
            _eventoUI.emit("Hola soy un evento enviado desde el ViewModel")

        }
    }
}