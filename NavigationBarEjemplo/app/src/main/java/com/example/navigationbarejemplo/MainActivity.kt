package com.example.navigationbarejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navigationbarejemplo.ui.theme.NavigationBarEjemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationBarEjemploTheme {
                // este metodo es del curso de jetpack en udemy
                MyApp()
            }
        }
    }
}