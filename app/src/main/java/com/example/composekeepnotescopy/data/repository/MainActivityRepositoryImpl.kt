package com.example.composekeepnotescopy.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.composekeepnotescopy.domain.MainActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor(val dataStore: DataStore<Preferences>) : MainActivityRepository {
    override suspend fun getThemeColor(): Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("THEME_COLOR")]
        }

}