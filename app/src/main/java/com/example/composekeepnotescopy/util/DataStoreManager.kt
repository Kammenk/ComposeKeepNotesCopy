package com.example.composekeepnotescopy.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class DataStoreManager(val context: Context) {

    val saveData: DataStore<Preferences> by preferencesDataStore(
        name = ""
    )

}