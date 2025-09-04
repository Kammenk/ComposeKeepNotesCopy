package com.example.composekeepnotescopy.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composekeepnotescopy.data.GetAllToDosUseCase
import com.example.composekeepnotescopy.data.Result
import com.example.composekeepnotescopy.domain.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(private val toDoRepository: ToDoRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(HistoryScreenUiState())
    val state = _state.asStateFlow()

    init {
        getAllToDos()
    }

    fun getAllToDos() {
        viewModelScope.launch {
            toDoRepository.getAllToDos().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.update { it.copy(isLoading = false) }
                        _state.update {
                            it.copy(toDos = result.data
                                .sortedByDescending { todo -> todo.creationDate }
                                .groupBy { todo -> todo.creationDate }
                                .toMap())
                        }
                    }

                    is Result.Error -> {
                        _state.update { it.copy(isLoading = false) }
                    }

                    is Result.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                }
            }
        }
    }
}