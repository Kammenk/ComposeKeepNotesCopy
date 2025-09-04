package com.example.composekeepnotescopy.data

import com.example.composekeepnotescopy.domain.ToDoRepository
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(toDo: ToDo) = toDoRepository.deleteToDo(toDo)
}