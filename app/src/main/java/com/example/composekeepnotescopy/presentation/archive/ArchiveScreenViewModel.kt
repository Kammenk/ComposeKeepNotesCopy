package com.example.composekeepnotescopy.presentation.archive

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArchiveScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<ArchiveScreenUiState>(ArchiveScreenUiState())
    val state = _state.asStateFlow()

}