package com.example.composekeepnotescopy.domain

import com.example.composekeepnotescopy.data.Result
import com.example.composekeepnotescopy.data.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun createToDo(toDo: ToDo): Result<Unit>

    suspend fun updateToDo(toDo: ToDo): Result<Unit>

    suspend fun deleteToDo(toDo: ToDo): Result<Unit>

    suspend fun getAllToDos(): Flow<Result<List<ToDo>>>

    suspend fun getTodayToDos(): Flow<Result<List<ToDo>>>

    suspend fun deleteAllToDos(): Result<Unit>
}