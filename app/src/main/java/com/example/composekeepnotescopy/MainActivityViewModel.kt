package com.example.composekeepnotescopy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composekeepnotescopy.domain.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val mainActivityRepository: MainActivityRepository): ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    init {
        isDarkTheme()
    }

    fun isDarkTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            mainActivityRepository.getThemeColor().collect { themeColor ->
                _state.value = _state.value.copy(isDarkTheme = themeColor)
            }
        }
    }
}