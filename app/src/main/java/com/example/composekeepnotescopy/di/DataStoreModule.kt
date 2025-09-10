package com.example.composekeepnotescopy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.example.composekeepnotescopy"
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

}