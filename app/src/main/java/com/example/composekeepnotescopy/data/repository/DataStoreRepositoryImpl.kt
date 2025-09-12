package com.example.composekeepnotescopy.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.composekeepnotescopy.domain.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(val dataStore: DataStore<Preferences>) : DataStoreRepository {

    private companion object {
        val addItemsToBottomPreferencesKey = booleanPreferencesKey("ADD_ITEMS_TO_BOTTOM")
        val moveCheckedItemsToBottomPreferencesKey =
            booleanPreferencesKey("MOVE_CHECKED_ITEMS_TO_BOTTOM")
        val displayRichLinkPreviewPreferencesKey =
            booleanPreferencesKey("DISPLAY_RICH_LINK_PREVIEW")
        val createTextNotesByDefaultPreferencesKey =
            booleanPreferencesKey("CREATE_TEXT_NOTES_BY_DEFAULT")
        val enableSharingPreferencesKey = booleanPreferencesKey("ENABLE_SHARING")

        val themeColorPreferencesKey = stringPreferencesKey("THEME_COLOR")
    }


    override suspend fun getAddItemsToBottomCheckedState(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[addItemsToBottomPreferencesKey]
        }


    override suspend fun setAddItemsToBottomCheckedState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[addItemsToBottomPreferencesKey] = value
        }
    }

    override suspend fun getMoveCheckedItemsToBottomCheckedState(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[moveCheckedItemsToBottomPreferencesKey]
        }


    override suspend fun setMoveCheckedItemsToBottomCheckedState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[moveCheckedItemsToBottomPreferencesKey] = value
        }
    }

    override suspend fun getDisplayRichLinkPreviewCheckedState(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[displayRichLinkPreviewPreferencesKey]
        }

    override suspend fun setDisplayRichLinkPreviewCheckedState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[displayRichLinkPreviewPreferencesKey] = value
        }
    }

    override suspend fun getCreateTextNotesByDefaultCheckedState(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[createTextNotesByDefaultPreferencesKey]
        }

    override suspend fun setCreateTextNotesByDefaultCheckedState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[createTextNotesByDefaultPreferencesKey] = value
        }
    }

    override suspend fun getEnableSharingCheckedState(): Flow<Boolean?> =
        dataStore.data.map { preferences ->
            preferences[enableSharingPreferencesKey]
        }

    override suspend fun setEnableSharingCheckedState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[enableSharingPreferencesKey] = value
        }
    }

    override suspend fun getThemeColor(): Flow<String?> =
        dataStore.data.map { preferences ->
            preferences[themeColorPreferencesKey]
        }

    override suspend fun setThemeColor(value: String) {
        dataStore.edit { preferences ->
            preferences[themeColorPreferencesKey] = value
        }
    }
}