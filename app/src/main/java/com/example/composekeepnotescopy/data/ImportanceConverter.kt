package com.example.composekeepnotescopy.data

import androidx.room.TypeConverter

class ImportanceConverter {

    @TypeConverter
    fun fromImportance(importance: ToDoImportance): Int {
        return importance.ordinal
    }

    @TypeConverter
    fun toImportance(importance: Int): ToDoImportance {
        return ToDoImportance.entries[importance]
    }
}