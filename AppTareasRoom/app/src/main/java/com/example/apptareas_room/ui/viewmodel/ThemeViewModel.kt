package com.example.apptareas_room.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareas_room.ui.theme.ThemeMode
import com.example.apptareas_room.ui.theme.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel //indicamos a hilt que este viewmodel puede recibir inyeccion de dependencias
class ThemeViewModel
@Inject constructor(
    private val prefs : ThemePreferences
): ViewModel(){
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode : StateFlow<ThemeMode> = _themeMode

    init {
        viewModelScope.launch {
            prefs.themeModeFlow.collect { mode ->
                _themeMode.value = mode
            }
        }
    }

    fun updateTheme(mode : ThemeMode){
        viewModelScope.launch {
            prefs.setTheme(mode)
        }
    }

}