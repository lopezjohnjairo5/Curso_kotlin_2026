package com.example.pasarargumentos.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute // 💡 Importante: Permite extraer los argumentos automáticamente
import com.example.pasarargumentos.screens.PantallaDetalle
import com.example.pasarargumentos.screens.PantallaInicio
import com.example.pasarargumentos.screens.PantallaLogin
import com.example.pasarargumentos.screens.PantallaPerfil
import com.example.pasarargumentos.screens.PantallaSettings

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        // 2. CONFIGURACIÓN DEL DESTINO INICIAL
        // Ahora pasamos la clase/objeto directamente en lugar del string "pantallaInicio"
        startDestination = PantallaLoginRoute,
        modifier = Modifier.padding(paddingValues)
    ) {

        // 3. PANTALLA DE INICIO
        // Estas rutas no tienen backStackEntry ya que no tienen argumentos salvo el navController, estas estan definidas como objetos en Routes.kt en lugar de class
        composable<PantallaLoginRoute> {
            PantallaLogin(navController = navController)
        }

        composable<PantallaInicioRoute> {
            PantallaInicio(navController = navController)
        }

        composable<PantallaPerfilRoute> {
            PantallaPerfil(navController = navController)
        }

        // 4. PANTALLA DE DETALLE CON ARGUMENTOS
        // Ya no necesitas 'arguments = listOf(navArgument(...))'. El compilador lee la data class.
        composable<PantallaDetalleRoute> { backStackEntry ->

            // Con '.toRoute<...>()' convertimos la ruta automáticamente en nuestro objeto de Kotlin
            val argumentos = backStackEntry.toRoute<PantallaDetalleRoute>()

            // Accedemos a los datos de manera directa y segura (sin el operador Elvis `?:` ni riesgo de nulos)
            PantallaDetalle(
                navController = navController,
                nombreUsuario = argumentos.nombreUsuario,
                edadUsuario = argumentos.edadUsuario
            )
        }

        composable<PantallaSettingsRoute> { backStackEntry ->
            val argumentos = backStackEntry.toRoute<PantallaSettingsRoute>()
            PantallaSettings(
                navController = navController,
                alias = argumentos.alias
            )
        }


    }
}
