package com.example.composekeepnotescopy.data

import androidx.room.TypeConverter
import java.time.LocalDate

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? = value?.let {
        LocalDate.ofEpochDay(it)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? = date?.toEpochDay()
}