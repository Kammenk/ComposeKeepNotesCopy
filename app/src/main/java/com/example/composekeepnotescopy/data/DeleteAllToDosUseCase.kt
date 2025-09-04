package com.example.composekeepnotescopy.data

import com.example.composekeepnotescopy.domain.ToDoRepository
import javax.inject.Inject

class DeleteAllToDosUseCase @Inject constructor(private val todoRepository: ToDoRepository) {
    suspend operator fun invoke() = todoRepository.deleteAllToDos()
}