package com.example.composekeepnotescopy.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SettingsScreen(modifier: Modifier) {
    val settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()
    val state by settingsScreenViewModel.state.collectAsStateWithLifecycle()

    SettingsScreenContent(modifier = modifier, state = state)
}


@Composable
fun SettingsScreenContent(modifier: Modifier, state: SettingsScreenUiState) {
    val addItemsToBottomCheckedState = remember { mutableStateOf(true) }
    val moveCheckedItemsToBottomCheckedState = remember { mutableStateOf(true) }
    val displayRichLinkPreviewCheckedState = remember { mutableStateOf(true) }
    val createTextNotesByDefaultCheckedState = remember { mutableStateOf(false) }
    val enableSharingCheckedState = remember { mutableStateOf(true) }

    Surface(modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                "Display Options",
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Add new items to bottom")
                Switch(
                    checked = addItemsToBottomCheckedState.value, onCheckedChange = { addItemsToBottomCheckedState.value = it }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Move checked items to bottom")
                Switch(
                    checked = moveCheckedItemsToBottomCheckedState.value, onCheckedChange = {
                        moveCheckedItemsToBottomCheckedState.value = it
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Display rich link preview")
                Switch(
                    checked = displayRichLinkPreviewCheckedState.value, onCheckedChange = {
                        displayRichLinkPreviewCheckedState.value = it
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Theme")
                Text("Light")
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                "Note creation", fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Create text notes by default")
                Switch(
                    checked = createTextNotesByDefaultCheckedState.value, onCheckedChange = {
                        createTextNotesByDefaultCheckedState.value = it
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Text("You can always long press to create other note types")
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                "Reminder defaults",
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Morning")
                Text("08:00")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Afternoon")
                Text("13:00")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Evening")
                Text("18:00")
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                "Sharing", fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable sharing")
                Switch(
                    checked = enableSharingCheckedState.value, onCheckedChange = {
                        enableSharingCheckedState.value = it
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreenContent(modifier = Modifier, state = SettingsScreenUiState())
}