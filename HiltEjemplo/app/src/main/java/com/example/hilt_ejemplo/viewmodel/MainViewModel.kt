package com.example.hilt_ejemplo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_ejemplo.data.RepositorioSimulado
import com.example.hilt_ejemplo.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

// ViewModel se puede invocar gracias a Hilt
@HiltViewModel //
class MainViewModel @Inject constructor(
    private val repositorio : RepositorioSimulado
): ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())

    val usuarios : StateFlow<List<Usuario>> = _usuarios

    init {
        cargarUsuarios()
    }

    fun cargarUsuarios(){
        // para ejecutar corrutinas
        viewModelScope.launch {
            delay(4000)
            _usuarios.value = repositorio.obtenerUsuarios()
        }
    }
}


/*
* @Inject = indica que la clase necesita algo
* @Module + @Provides = es quien le provee al Inject lo que necesita
* @InstallIn = Crea un contenedor
*
* Flujo:
*@Inject indica a hilt que el MainViewModel necesita un RepositorioSimulado,
* hilt va a buscarlo en toda la app y donde encuentre @Module + @Provides
* verifica si está ahí lo que se pide y luego @InstallIn gestiona|comprueba
* el proceso. Al heredar de ViewModel nos aseguramos que sobreviva a cambios de configuracion
* y maneje la logica en la interface de usuario.
*
* */