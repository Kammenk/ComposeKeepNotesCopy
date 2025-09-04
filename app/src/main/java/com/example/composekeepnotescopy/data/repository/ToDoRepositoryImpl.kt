package com.example.composekeepnotescopy.data.repository

import com.example.composekeepnotescopy.data.Result
import com.example.composekeepnotescopy.data.ToDo
import com.example.composekeepnotescopy.data.db.ToDoDao
import com.example.composekeepnotescopy.domain.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository {

    override suspend fun createToDo(toDo: ToDo): Result<Unit> = try {
        toDoDao.createToDo(toDo)
        Result.Success(Unit)
    } catch (ex: Exception) {
        Result.Error(ex)
    }

    override suspend fun updateToDo(toDo: ToDo): Result<Unit> = try {
        toDoDao.updateToDo(toDo)
        Result.Success(Unit)
    } catch (ex: Exception) {
        Result.Error(ex)
    }

    override suspend fun deleteToDo(toDo: ToDo): Result<Unit> = try {
        toDoDao.deleteToDo(toDo)
        Result.Success(Unit)
    } catch (ex: Exception) {
        Result.Error(ex)
    }

    override suspend fun getAllToDos(): Flow<Result<List<ToDo>>> = flow {
        emit(Result.Loading)

        try {
            toDoDao.getAllToDos().collect { toDos ->
                emit(Result.Success(toDos))
            }
        } catch (ex: Exception) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getTodayToDos(): Flow<Result<List<ToDo>>> = flow {
        emit(Result.Loading)

        try {
            toDoDao.getTodayToDos().collect { toDos ->
                emit(Result.Success(toDos))
            }
        } catch (ex: Exception) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun deleteAllToDos(): Result<Unit> = try {
        toDoDao.deleteAllToDos()
        Result.Success(Unit)
    } catch (ex: Exception) {
        Result.Error(ex)
    }
}