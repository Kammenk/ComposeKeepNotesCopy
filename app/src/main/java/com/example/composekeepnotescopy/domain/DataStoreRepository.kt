package com.example.composekeepnotescopy.domain

import com.example.composekeepnotescopy.data.Result
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun getAddItemsToBottomCheckedState(): Flow<Boolean?>
    suspend fun setAddItemsToBottomCheckedState(value: Boolean)

    suspend fun getMoveCheckedItemsToBottomCheckedState(): Flow<Boolean?>
    suspend fun setMoveCheckedItemsToBottomCheckedState(value: Boolean)

    suspend fun getDisplayRichLinkPreviewCheckedState(): Flow<Boolean?>
    suspend fun setDisplayRichLinkPreviewCheckedState(value: Boolean)

    suspend fun getCreateTextNotesByDefaultCheckedState(): Flow<Boolean?>
    suspend fun setCreateTextNotesByDefaultCheckedState(value: Boolean)

    suspend fun getEnableSharingCheckedState(): Flow<Boolean?>
    suspend fun setEnableSharingCheckedState(value: Boolean)
    suspend fun getThemeColor(): Flow<String?>
    suspend fun setThemeColor(value: String)
}