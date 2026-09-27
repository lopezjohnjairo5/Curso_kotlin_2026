package com.example.apptareas_room.ui.ajustes

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptareas_room.ui.theme.ThemeSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesScreen(
    navController: NavController
){
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text( text = "Ajustes") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }

    ) {
        paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(16.dp)

        ) {
            Text(
                text = "Apariencia",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            ThemeSelector()
        }
    }
}