package com.example.composekeepnotescopy.presentation.settings

import androidx.lifecycle.ViewModel
import com.example.composekeepnotescopy.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(val dataStoreManager: DataStoreManager): ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenUiState())
    val state = _state.asStateFlow()

    fun saveToDataStore() {
        dataStoreManager
    }
}