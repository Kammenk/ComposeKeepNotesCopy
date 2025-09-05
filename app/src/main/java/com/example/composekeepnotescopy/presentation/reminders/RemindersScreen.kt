package com.example.composekeepnotescopy.presentation.reminders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun RemindersScreen(modifier: Modifier) {
    val remindersScreenViewModel: RemindersScreenViewModel = hiltViewModel()
    val state by remindersScreenViewModel.state.collectAsStateWithLifecycle()

    RemindersScreenContent(modifier, state)
}

@Composable
fun RemindersScreenContent(modifier: Modifier, state: RemindersScreenUiState) {

}
