package com.example.apptareas_room

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // le indica a Hilt que debe crear el contenedor de dependencias desde el inicio de la aplicacion
class TareaApp : Application()

