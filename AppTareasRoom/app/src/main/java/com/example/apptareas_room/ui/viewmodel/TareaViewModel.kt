package com.example.apptareas_room.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas_room.data.local.entity.TareaEntity
import com.example.apptareas_room.data.repository.TareaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// @HiltViewModel = indica a hilt que esta clase puede recibir dependencias
// @Inject = Inyeccion de dependencias, notación de hilt, indica que necesita una dependendia, en este caso TareaRepository

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val repository: TareaRepository
) : ViewModel(){
    private val _tareas = MutableStateFlow<List<TareaEntity>>(emptyList())
    val tareas: StateFlow<List<TareaEntity>> = _tareas // publico

    // se ejecuta una sola vez al crear el viewmodel
    init {
        viewModelScope.launch {
            repository.obtenerTodasLasTareas().collect {
                _tareas.value = it // it es la lista de tareas emitidas por el flow
            }
        }
    }

    fun insertarTarea(tarea: TareaEntity){
        viewModelScope.launch { repository.insertarTarea(tarea) }
    }

    fun eliminarTarea(tarea: TareaEntity){
        viewModelScope.launch { repository.eliminarTarea(tarea) }
    }
}