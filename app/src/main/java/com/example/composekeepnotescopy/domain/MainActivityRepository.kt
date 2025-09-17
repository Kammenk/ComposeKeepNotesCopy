package com.example.composekeepnotescopy.domain

import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {

    suspend fun getThemeColor(): Flow<String?>
}