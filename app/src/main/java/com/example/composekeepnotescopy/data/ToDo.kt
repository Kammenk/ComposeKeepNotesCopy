package com.example.composekeepnotescopy.data

import android.icu.util.Calendar
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.composekeepnotescopy.util.DateUtils
import java.time.LocalDate
import java.util.Date


@Entity(tableName = "Todos")
@TypeConverters(DateConverter::class, ImportanceConverter::class)
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "importance")
    val importance: ToDoImportance = ToDoImportance.LOW,
    @ColumnInfo(name = "creation_date")
    val creationDate: LocalDate = DateUtils.getTodaysDate(),
    @ColumnInfo(name = "completion_date")
    val completionDate: LocalDate? = null,
    @ColumnInfo(name = "is_reoccurring")
    val isReoccurring: Boolean = false,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false
)
