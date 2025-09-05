package com.example.composekeepnotescopy.presentation.reminders

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RemindersScreenViewModel @Inject constructor() : ViewModel()  {

    private val _state = MutableStateFlow<RemindersScreenUiState>(RemindersScreenUiState())
    val state = _state.asStateFlow()
}