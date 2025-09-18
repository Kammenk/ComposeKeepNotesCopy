package com.example.composekeepnotescopy.domain

import kotlinx.coroutines.flow.Flow

interface MainActivityRepository {

    suspend fun getThemeColor(): Flow<String?>

    suspend fun getListViewState(): Flow<Boolean?>
    suspend fun setListViewState(isGrid: Boolean)
}