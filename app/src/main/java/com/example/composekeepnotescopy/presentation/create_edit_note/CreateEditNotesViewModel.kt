package com.example.composekeepnotescopy.presentation.create_edit_note

import androidx.lifecycle.ViewModel
import com.example.composekeepnotescopy.presentation.settings.SettingsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEditNotesViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CreateEditNotesUiState())
    val state = _state.asStateFlow()

    init {}

    fun onEvent(value: CreateEditNotesUiEvent) {}

    sealed interface CreateEditNotesUiEvent {
    }

    data class ReminderInfo(val time: SettingsScreenViewModel.ReminderData, val isRepeating: Boolean)
}
