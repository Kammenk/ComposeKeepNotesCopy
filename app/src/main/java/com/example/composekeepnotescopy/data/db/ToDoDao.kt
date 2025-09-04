package com.example.composekeepnotescopy.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.composekeepnotescopy.data.ToDo
import com.example.composekeepnotescopy.util.DateUtils
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert
    suspend fun createToDo(toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM Todos")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("SELECT * FROM Todos WHERE creation_date = :date")
    fun getTodayToDos(date: Long = DateUtils.getTodaysDate().toEpochDay()): Flow<List<ToDo>>

    @Query("DELETE FROM Todos")
    suspend fun deleteAllToDos()
}