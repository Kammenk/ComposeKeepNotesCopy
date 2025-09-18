package com.example.composekeepnotescopy.presentation.reminders

import com.example.composekeepnotescopy.data.ToDo

data class RemindersScreenUiState(
    val isLoading: Boolean = false,
    val remindersList: List<ToDo> = emptyList(),
    val isError: Boolean = false
)