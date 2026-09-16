package com.example.apptareas_room.data.repository

import com.example.apptareas_room.data.local.dao.TareaDao
import com.example.apptareas_room.data.local.entity.TareaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// intermediario entre la fuente de datos y el viewmodel

//@Inject = notación de hilt, indica que necesita una dependendia, en este caso TareaDao
class TareaRepository @Inject constructor(
    private val tareaDao: TareaDao
){
    // funciones suspendidas, serán llamadas desde corrutinas

    // inserta una tarea en la BD
    suspend fun insertarTarea(tarea : TareaEntity){
        tareaDao.insertarTarea(tarea)
    }

    // actualiza una tarea existente en la BD
    suspend fun actualizarTarea(tarea: TareaEntity){
        tareaDao.actualizarTarea(tarea)
    }

    // elimina una tarea especifica de la BD, pasada por parametro
    suspend fun eliminarTarea(tarea: TareaEntity){
        tareaDao.eliminarTarea(tarea)
    }

    suspend fun eliminarTodasLasTareas(){
        tareaDao.eliminarTodasLasTareas()
    }

    // es asincrona gracias al FLOW, por lo cual no requiere que sea suspend
    fun obtenerTodasLasTareas() : Flow<List<TareaEntity>>{
        return tareaDao.obtenerTodasLasTareas()
    }
}