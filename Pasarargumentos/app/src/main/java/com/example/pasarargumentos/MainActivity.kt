package com.example.pasarargumentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pasarargumentos.ui.theme.PasarArgumentosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasarArgumentosTheme {
                /**
                 * ===================================================================================
                 * 📝 GUÍA DE REFERENCIA: MIGRACIÓN A NAVEGACIÓN MODERNA Y SEGURA (TYPE-SAFE)
                 * ===================================================================================
                 *
                 * El cierre inesperado ("keeps stopping") ocurría porque Jetpack Compose Navigation
                 * utiliza internamente la infraestructura de "Kotlinx Serialization" para convertir
                 * nuestras clases y objetos de ruta (como 'PantallaDetalleRoute') en datos que el
                 * sistema pueda transferir. Sin la configuración correcta en Gradle, el compilador
                 * no sabe cómo interpretar la anotación `@Serializable`.
                 *
                 * ===================================================================================
                 * 🚀 EXPLICACIÓN: ¿CÓMO FUNCIONA EL PASO DE ARGUMENTOS AHORA?
                 * ===================================================================================
                 *
                 * 🔹 Enfoque Antiguo (Basado en Strings):
                 *    Antes tenías que escribir rutas como "pantallaDetalle/{nombreUsuario}/{edadUsuario}".
                 *    - Desventaja 1: Si escribías mal una letra en el origen o el destino, la app fallaba.
                 *    - Desventaja 2: Tenías que extraer los datos manualmente con métodos como
                 *      `getString("nombreUsuario")`, que devuelven valores nulos y te obligaban a usar
                 *      el operador Elvis (`?:`).
                 *    - Desventaja 3: Si te faltaba pasar un argumento (como te pasó con la edad en tu
                 *      código original), el compilador no te avisaba y la app crasheaba en ejecución.
                 *
                 * 🔹 Enfoque Moderno (Type-Safe):
                 *    Ahora defines una estructura pura de Kotlin: `data class PantallaDetalleRoute(val nombreUsuario: String, val edadUsuario: Int)`.
                 *    - Ventaja 1: El compilador valida los datos. Si intentas navegar sin poner la edad,
                 *      Android Studio te marcará un error en rojo inmediatamente ANTES de compilar.
                 *    - Ventaja 2: Cero nulos. Al usar `.toRoute<PantallaDetalleRoute>()`, el sistema
                 *      reconstruye la clase completa. Los datos mantienen sus tipos nativos (`String` e `Int`)
                 *      de forma automática, eliminando la necesidad de validaciones `?:` o conversiones manuales.
                 *
                 * ===================================================================================
                 * 🛠️ CONFIGURACIÓN TÉCNICA REALIZADA EN GRADLE:
                 * ===================================================================================
                 *
                 * 1️⃣ EN EL ARCHIVO DE CATÁLOGO DE VERSIONES (`libs.versions.toml`):
                 *    -------------------------------------------------------------------------------
                 *    • Se definió la versión de la librería JSON: `kotlinxSerialization = "1.7.3"`.
                 *    • Se añadió la dependencia en [libraries] para poder invocarla en el proyecto:
                 *      `kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", ... }`
                 *    • Se dio de alta el plugin oficial en [plugins], enlazándolo a tu misma versión
                 *      de Kotlin (2.2.10):
                 *      `kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", ... }`
                 *
                 * 2️⃣ EN EL ARCHIVO GRADLE DE MÓDULO (`build.gradle.kts`):
                 *    -------------------------------------------------------------------------------
                 *    • Bloque `plugins {}`: Se aplicó el plugin mediante su alias:
                 *      `alias(libs.plugins.kotlin.serialization)`
                 *      (Esto le enseña a Kotlin a generar el código oculto necesario para serializar).
                 *
                 *    • Bloque `dependencies {}`: Se implementó la librería JSON:
                 *      `implementation(libs.kotlinx.serialization.json)`
                 *      (Esto le adecúa a Compose las herramientas en tiempo de ejecución para leer los datos).
                 *
                 * 3️⃣ EN EL ARCHIVO DE NAVEGACIÓN (`NavGraph.kt`):
                 *    -------------------------------------------------------------------------------
                 *    • Se reemplazó el antiguo método manual de extracción por la función de extensión:
                 *      `val argumentos = backStackEntry.toRoute<PantallaDetalleRoute>()`
                 *
                 * ===================================================================================
                 * 💡 REGLA DE ORO PARA EL FUTURO:
                 * Cada vez que uses clases con `@Serializable` para navegar, tu proyecto SIEMPRE
                 * requerirá que el plugin "kotlin.serialization" esté activo y sincronizado en Gradle.
                 * ===================================================================================
                 */

                MyApp()
            }
        }
    }
}
