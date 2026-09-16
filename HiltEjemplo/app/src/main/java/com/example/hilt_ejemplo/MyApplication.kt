package com.example.hilt_ejemplo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Application() se ejecuta de primeras, antes que cualquier otra parte de la app
@HiltAndroidApp // esta es una notacion que indica que esta es la clase principal de la aplicacion
class MyApplication : Application()


/*
* @HiltAndroidApp
* Le dice a hilt por favor crea un contenedor
* global de dependencias que viva mientras la
* app esté activa.
* */