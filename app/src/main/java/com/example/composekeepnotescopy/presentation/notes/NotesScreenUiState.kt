package com.example.composekeepnotescopy.presentation.notes

import com.example.composekeepnotescopy.data.ToDo

data class NotesScreenUiState(
    val isLoading: Boolean = false,
    val toDoList: List<ToDo> = emptyList(),
    val errorMessage: String? = ""
)