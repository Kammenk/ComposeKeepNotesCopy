package com.example.composekeepnotescopy.presentation.create_edit_note

import android.graphics.Bitmap
import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CreateEditNotesScreen(modifier: Modifier) {
    val createEditNotesViewModel: CreateEditNotesViewModel = hiltViewModel()
    val state by createEditNotesViewModel.state.collectAsStateWithLifecycle()

    CreateEditNotesScreenContent(modifier, state = state, createEditNotesViewModel::onEvent)
}


@Composable
fun CreateEditNotesScreenContent(
    modifier: Modifier,
    state: CreateEditNotesUiState,
    onEvent: (CreateEditNotesViewModel.CreateEditNotesUiEvent) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Image(bitmap = ImageBitmap(0, 0), contentDescription = "")
            TextField(modifier = Modifier.fillMaxWidth(), value = state.title, onValueChange = {})
            TextField(modifier = Modifier.fillMaxWidth(), value = state.note, onValueChange = {})
            Row(modifier = Modifier.fillMaxWidth()) {
                if (state.reminderInfo != null) {
                    AssistChip(onClick = {}, label = { Text("") }, leadingIcon = {})

                }
                if (state.labels.isNotEmpty()) {
                    state.labels.forEach { labelName ->
                        AssistChip(onClick = {}, label = { Text(labelName) })
                    }
                }

                if (state.noteColor != null) {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Circle, contentDescription = "", tint = state.noteColor)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateEditNotesScreenContentPreview() {
    CreateEditNotesScreenContent(
        modifier = Modifier, state =
            CreateEditNotesUiState(
                image = null,
                title = "Ala bala title",
                note = "Ala bala note"
            ),
        onEvent = {}
    )
}