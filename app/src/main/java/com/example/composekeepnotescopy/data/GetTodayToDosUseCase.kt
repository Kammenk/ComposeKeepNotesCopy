package com.example.composekeepnotescopy.data

import com.example.composekeepnotescopy.domain.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {
    suspend operator fun invoke(): Flow<Result<List<ToDo>>> = toDoRepository.getTodayToDos()
}