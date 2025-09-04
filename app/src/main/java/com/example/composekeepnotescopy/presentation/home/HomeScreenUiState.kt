package com.example.composekeepnotescopy.presentation.home

import com.example.composekeepnotescopy.data.ToDo

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val toDoList: List<ToDo> = emptyList(),
    val errorMessage: String? = ""
)