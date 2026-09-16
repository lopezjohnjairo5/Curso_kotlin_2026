package com.example.apptareas_room.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.apptareas_room.data.local.entity.TareaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao{
    // indicamos que se insertará un registro, si existe un conflicto (registro con mismo ID por ejemplo) se reemplazara
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTarea(tarea: TareaEntity) // funcion suspendida es decir se ejecuta mediante corrutinas

    // consulta para traer todas las tareas de la bd local
    @Query("SELECT * FROM tareas ORDER BY id DESC")
    fun obtenerTodasLasTareas() : Flow<List<TareaEntity>> // funcion que retorna flujo reactivo en tiempo real, es decir cada que se actualice la tabla retorna, no requiere suspen debido a que se usa flow

    // consulta para actualizar una tarea
    @Update
    suspend fun actualizarTarea(tarea: TareaEntity)

    // elimina una tarea de la BD local
    @Delete
    suspend fun eliminarTarea(tarea: TareaEntity) // elimina una tarea especifica de la BD

    // borra todas las tareas de la BD local
    @Query("DELETE FROM tareas")
    suspend fun eliminarTodasLasTareas()
}