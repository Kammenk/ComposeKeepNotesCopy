package com.example.composekeepnotescopy.presentation.create_label

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateLabelScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<CreateLabelScreenUiState>(CreateLabelScreenUiState())
    val state = _state.asStateFlow()
}