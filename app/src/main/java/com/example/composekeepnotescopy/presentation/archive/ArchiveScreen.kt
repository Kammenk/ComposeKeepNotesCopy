package com.example.composekeepnotescopy.presentation.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composekeepnotescopy.presentation.reminders.RemindersScreenContent
import com.example.composekeepnotescopy.presentation.reminders.RemindersScreenUiState
import com.example.composekeepnotescopy.ui.theme.Orange

@Composable
internal fun ArchiveScreen(modifier: Modifier) {
    val archiveScreenViewModel: ArchiveScreenViewModel = hiltViewModel()
    val state by archiveScreenViewModel.state.collectAsStateWithLifecycle()

    ArchiveScreenContent(modifier, state)
}

@Composable
fun ArchiveScreenContent(modifier: Modifier, state: ArchiveScreenUiState) {
    if (state.archivesList.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Archive,
                contentDescription = "LightBulb",
                modifier = Modifier
                    .height(140.dp)
                    .width(140.dp), tint = Orange
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Your archived notes appear here",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ArchiveScreenContentPreview() {
    ArchiveScreenContent(modifier = Modifier, state = ArchiveScreenUiState(isLoading = false, archivesList = emptyList(), isError = false))
}
