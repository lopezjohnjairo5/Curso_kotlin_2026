package com.example.apptareas_room.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptareas_room.data.local.dao.TareaDao
import com.example.apptareas_room.data.local.entity.TareaEntity

// indica a room que esta serpa la base de datos principal de la aplicacion
@Database(
    entities = [TareaEntity::class], // indicamos las entidades o tablas que conforman la BD
    version = 1, // numero de version de la BD, se incrementa en 1 cada que se cambia la estructura de la BD, por ejemplo quitar o agregar un campo en la tabla tareas
    exportSchema = false // indicamos que no se quiere crear un archivo con la estructura de la BD
)


abstract class TareaDatabase : RoomDatabase(){
    abstract fun tareaDao() : TareaDao //funcion sin cuerpo y que se implementa automaticamente, retorna un OBJ TareaDao
}