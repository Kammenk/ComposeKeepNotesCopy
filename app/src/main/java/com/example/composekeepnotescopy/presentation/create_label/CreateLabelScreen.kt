package com.example.composekeepnotescopy.presentation.create_label

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CreateLabelScreen(modifier: Modifier) {
    val createLabelScreenViewModel: CreateLabelScreenViewModel = hiltViewModel()
    val state by createLabelScreenViewModel.state.collectAsStateWithLifecycle()

    CreateLabelScreenContent(modifier, state)
}

@Composable
fun CreateLabelScreenContent(modifier: Modifier, state: CreateLabelScreenUiState) {

}
