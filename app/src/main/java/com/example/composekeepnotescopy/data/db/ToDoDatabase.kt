package com.example.composekeepnotescopy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composekeepnotescopy.data.ToDo

@Database(entities = [ToDo::class], version = 2, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}