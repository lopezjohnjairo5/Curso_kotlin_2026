package com.example.compartirviewmodel

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
import com.example.compartirviewmodel.navigation.AppNavHost
import com.example.compartirviewmodel.ui.theme.CompartirViewModelTheme
import com.example.compartirviewmodel.view.PantallaA

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompartirViewModelTheme {
                /*
                * En este ejemplo
                * compartimos el mismo viewmodel
                * entre 2 pantallas por lo cual
                * se puede modificar un dato y mantener su valor
                * entre las diferentes pantallas ademas de modificarlo
                * segun se requiera.
                * */
                AppNavHost() // invocamos el sistema de navegacion primero
            }
        }
    }
}

