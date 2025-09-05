package com.example.composekeepnotescopy.presentation.archive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ArchiveScreen(modifier: Modifier) {
    val archiveScreenViewModel: ArchiveScreenViewModel = hiltViewModel()
    val state by archiveScreenViewModel.state.collectAsStateWithLifecycle()

    ArchiveScreenContent(modifier, state)
}

@Composable
fun ArchiveScreenContent(modifier: Modifier, state: ArchiveScreenUiState) {

}
