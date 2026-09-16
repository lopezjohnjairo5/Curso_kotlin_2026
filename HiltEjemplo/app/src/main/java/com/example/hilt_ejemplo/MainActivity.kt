package com.example.hilt_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hilt_ejemplo.ui.theme.HiltEjemploTheme
import com.example.hilt_ejemplo.view.PantallaPrincipal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // para indicarle a Hilt que aqui se pueden inyectar dependencias.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /**
             * ============================================================================
             * 📄 build.gradle.kts (Project: Hilt_-_Ejemplo)
             * ============================================================================
             * ¿QUÉ ES?: El centro de control global de la compilación de todo el proyecto.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Registra los plugins globales del ecosistema (Android, Kotlin, KSP, Hilt).
             * - Define qué herramientas base estarán disponibles para los módulos de la app.
             * - Centraliza las reglas de construcción de alto nivel de todo el software.
             */


            /**
             * ============================================================================
             * 📄 build.gradle.kts (Module :app)
             * ============================================================================
             * ¿QUÉ ES?: La receta de construcción específica de tu aplicación móvil.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Configura las propiedades de la app (ApplicationId, CompileSdk, TargetSdk).
             * - Modifica las variantes de compilación (como la firma digital en Release).
             * - Lista explícitamente todas las librerías a usar (Compose, Coil, Hilt, etc.).
             */


            /**
             * ============================================================================
             * 📄 gradle.properties (Project Properties)
             * ============================================================================
             * ¿QUÉ ES?: El archivo de variables de entorno y banderas de comportamiento.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Activa configuraciones globales del sistema (como "android.useAndroidX").
             * - Habilita la compilación paralela para que el proyecto compile más rápido.
             * - Asigna la cantidad de memoria RAM máxima que puede usar el motor de Gradle.
             */


            /**
             * ============================================================================
             * 📄 gradle-wrapper.properties (Gradle Version)
             * ============================================================================
             * ¿QUÉ ES?: El anclaje de la versión del motor de compilación de Gradle.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Define la URL y la versión exacta de Gradle requerida para compilar la app.
             * - Asegura que el proyecto compile igual en cualquier computadora o servidor.
             * - Descarga automáticamente el motor correcto al abrir el proyecto por primera vez.
             */


            /**
             * ============================================================================
             * 📄 libs.versions.toml (Version Catalog "libs")
             * ============================================================================
             * ¿QUÉ ES?: El catálogo centralizado y unificado de dependencias del proyecto.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Evita tener números de versiones duplicados en múltiples archivos Gradle.
             * - Divide la configuración en bloques limpios: [versions], [libraries] y [plugins].
             * - Facilita la actualización de librerías modificando una sola línea de texto.
             */


            /**
             * ============================================================================
             * 📄 local.properties (SDK Location)
             * ============================================================================
             * ¿QUÉ ES?: El mapa de rutas privado de tu entorno de desarrollo local.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Le dice a Android Studio la ruta exacta en tu disco donde está el Android SDK.
             * - Guarda datos privados de la computadora actual (como llaves de APIs o NDK).
             * - NUNCA SE SUBE A GITHUB (cada desarrollador tiene rutas de carpetas distintas).
             */


            /**
             * ============================================================================
             * 📄 settings.gradle.kts (Project Settings)
             * ============================================================================
             * ¿QUÉ ES?: El mapa de estructura e inicialización del proyecto de Android.
             *
             * ¿PARA QUÉ SIRVE?:
             * - Le indica a Gradle cuáles son los módulos activos (por ejemplo: include(":app")).
             * - Asigna el nombre oficial del proyecto que verá el sistema de compilación.
             * - Define los repositorios de internet (Google, MavenCentral) para bajar librerías.
             */

            HiltEjemploTheme {
                PantallaPrincipal()
            }
        }
    }
}


