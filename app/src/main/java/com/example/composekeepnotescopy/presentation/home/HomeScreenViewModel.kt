package com.example.composekeepnotescopy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composekeepnotescopy.data.Result
import com.example.composekeepnotescopy.data.ToDo
import com.example.composekeepnotescopy.domain.ToDoRepository
import com.example.composekeepnotescopy.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   private val toDoRepository: ToDoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    init {
        getTodayTodos()
    }

    fun getTodayTodos() {
        viewModelScope.launch {
            toDoRepository.getTodayToDos().collect { result ->
                DateUtils.getTodaysDate()
                when (result) {
                    is Result.Success -> {
                        _state.update { it.copy(isLoading = false) }
                        _state.update { it.copy(toDoList = result.data) }
                    }

                    is Result.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _state.update { it.copy(errorMessage = result.exception.message) }
                    }

                    is Result.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun createToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.createToDo(toDo)
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(toDo)
        }

    }

    fun deleteAllToDos() {
        viewModelScope.launch {
            toDoRepository.deleteAllToDos()
        }
    }
}