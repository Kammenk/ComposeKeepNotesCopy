package com.example.composekeepnotescopy.data

import com.example.composekeepnotescopy.domain.ToDoRepository
import javax.inject.Inject

class GetAllToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke() = toDoRepository.getAllToDos()
}