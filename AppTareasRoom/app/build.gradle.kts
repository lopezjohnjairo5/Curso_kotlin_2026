import org.jetbrains.kotlin.gradle.dsl.JvmTarget // <-- 1. ¡OBLIGATORIO AGREGAR ESTE IMPORT EN LA LÍNEA 1!

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)  // <-- 1. Agrega el plugin de Hilt aquí
    // alias(libs.plugins.kotlin.android) //A partir de AGP 9, Android ya incluye y gestiona el soporte de Kotlin de forma nativa. Intentar declararlo manualmente causa un conflicto de duplicidad que rompe la sincronización.
}

hilt{
    enableAggregatingTask = false
}

android {
    namespace = "com.example.apptareas_room"
    compileSdk = 37 // Se simplifica la sintaxis para estabilidad directa

    defaultConfig {
        applicationId = "com.example.apptareas_room"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    // ¡Aquí adentro ya NO va kotlinOptions!
}

// 2. NUEVO BLOQUE ESTÁNDAR PARA AGP 9 Y KOTLIN 2.2+ (FUERA DE ANDROID)
kotlin {
    //  En AGP 9, el bloque antiguo fue completamente eliminado para separar las herramientas de Android de las de Kotlin. Ahora, la configuración del compilador de Kotlin se realiza de manera global y limpia fuera del bloque de Android.
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17) // Configura la compatibilidad de Kotlin con Java 17
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Configuración de Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // KSP procesará tanto Room como Hilt de forma ultra rápida
    implementation(libs.hilt.navigation.compose)

    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)

    implementation(libs.navController)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
