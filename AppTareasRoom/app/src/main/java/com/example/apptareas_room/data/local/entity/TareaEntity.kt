package com.example.apptareas_room.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// definimos la tabla tareas como una entidad
@Entity(tableName = "tareas")
data class TareaEntity( // aquí creamos la estructura de la tabla, Esta seccion es el constructor primario
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descripcion: String ?= null, // ?= null indica que es un campo opcional, puede ser null
    val completada: Boolean = false,
    val fechaCreacion: String
)