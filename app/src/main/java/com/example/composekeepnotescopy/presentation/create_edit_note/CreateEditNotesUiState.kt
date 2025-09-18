package com.example.composekeepnotescopy.presentation.create_edit_note

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color

data class CreateEditNotesUiState(
    val image: Drawable? = null,
    val title: String = "",
    val note: String = "",
    val isPinned: Boolean = false,
    val reminderInfo: CreateEditNotesViewModel.ReminderInfo? = null,
    val labels: List<String> = emptyList(),
    val noteColor: Color? = null,
    val noteBackground: Drawable? = null
)