package com.example.composekeepnotescopy.presentation.trash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun TrashScreen(modifier: Modifier) {
    val trashScreenViewModel: TrashScreenViewModel = hiltViewModel()
    val state by trashScreenViewModel.state.collectAsStateWithLifecycle()

    TrashScreenContent(modifier, state)
}

@Composable
fun TrashScreenContent(modifier: Modifier, state: TrashScreenUiState) {

}
