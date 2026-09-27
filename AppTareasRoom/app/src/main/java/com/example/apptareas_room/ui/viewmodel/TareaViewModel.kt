package com.example.apptareas_room.ui.viewmodel

import android.util.Log
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

    private val _tareaSeleccionada = MutableStateFlow<TareaEntity?>(null) // alamacena el estado actual de la tarea a editar
    val tareaSeleccionada : StateFlow<TareaEntity?> = _tareaSeleccionada

    private val _tareasFiltradas = MutableStateFlow<List<TareaEntity>>(emptyList())
    val tareasFiltradas : StateFlow<List<TareaEntity>> = _tareasFiltradas

    // almacena una tarea previamente eliminada, usada para restablecer la tarea eliminada
    private var tareaEliminada : TareaEntity ?= null


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

    fun eliminarTarea(tarea: TareaEntity, texto :String){
        viewModelScope.launch {
            tareaEliminada = tarea // guardamos la tarea eliminada
            repository.eliminarTarea(tarea)
            buscarTareas(texto)
        }
    }

    fun actualizarTarea(tarea: TareaEntity){
        viewModelScope.launch { // lanzamos una corrutina y llamamos al repositorio el cual llama al dao
            repository.actualizarTarea(tarea)
        }
    }

    fun cargarTareaPorId(id: Int){
        viewModelScope.launch { // obtenemos una tarea por ID
            val tarea = repository.obtenerTareaPorId(id)
            Log.d("ID", tarea?.id.toString())
            _tareaSeleccionada.value = tarea
        }
    }

    fun buscarTareas(query: String){
        viewModelScope.launch{
            val resultado = repository.buscarTareas(query)
            _tareasFiltradas.value = resultado
        }
    }

    // se ejecuta al presionar deshacer,
    fun restaurarTarea(texto : String){
        viewModelScope.launch {
            // si hay una tarea eliminada, es decir sino es nulo
            tareaEliminada?.let{
                repository.insertarTarea(it) // insertamos la tarea que se eliminó
                tareaEliminada = null // limpiamos la variable que almacenaba la tarea eliminada
                buscarTareas(texto) // volvemos a buscar para que se actualice el listado y aparezca nuevamente la tarea previamente restaurada
            }
        }
    }
}