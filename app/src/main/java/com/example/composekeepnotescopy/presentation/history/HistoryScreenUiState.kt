package com.example.composekeepnotescopy.presentation.history

import com.example.composekeepnotescopy.data.ToDo
import java.time.LocalDate

data class HistoryScreenUiState(
    val isLoading: Boolean = false,
    val toDos: Map<LocalDate, List<ToDo>> = mapOf()
)