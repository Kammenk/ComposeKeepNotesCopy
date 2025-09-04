package com.example.composekeepnotescopy.util

import java.time.LocalDate
import java.util.Calendar
import java.util.Date

object DateUtils {

    fun getTodaysDate(): LocalDate {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val localDate = LocalDate.of(year, month, day)
        return localDate
    }

    fun otherTime() {
        val date = Date().time
    }
}