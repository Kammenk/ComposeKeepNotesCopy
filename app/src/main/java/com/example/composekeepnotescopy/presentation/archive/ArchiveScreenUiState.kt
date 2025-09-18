package com.example.composekeepnotescopy.presentation.archive

import com.example.composekeepnotescopy.data.ToDo

data class ArchiveScreenUiState (
    val isLoading: Boolean = false,
    val archivesList: List<ToDo> = emptyList(),
    val isError: Boolean = false
)