package com.example.composekeepnotescopy.presentation.settings

data class SettingsScreenUiState(
    val addItemsToBottomChecked: Boolean = false,
    val moveCheckedItemsToBottomChecked: Boolean = false,
    val displayRichLinkPreviewChecked: Boolean = false,
    val createTextNotesByDefaultChecked: Boolean = false,
    val enableSharingChecked: Boolean = false
)