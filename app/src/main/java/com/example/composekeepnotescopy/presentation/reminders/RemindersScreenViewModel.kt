package com.example.composekeepnotescopy.presentation.reminders

import androidx.lifecycle.ViewModel
import com.example.composekeepnotescopy.data.ToDo
import com.example.composekeepnotescopy.domain.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RemindersScreenViewModel @Inject constructor(val toDoRepository: ToDoRepository) : ViewModel()  {

    private val _state = MutableStateFlow(RemindersScreenUiState())
    val state = _state.asStateFlow()


    init {
        getReminders()
    }

    fun getReminders() {

    }
}