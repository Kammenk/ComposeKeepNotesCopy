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
    Surface(modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                "Display Options",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Add new items to bottom")
                Switch(checked = true, onCheckedChange = {}, colors = SwitchDefaults.colors(
                    checkedTrackColor = Color.Blue
                ))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Move checked items to bottom")
                Switch(checked = true, onCheckedChange = {})
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Display rich link preview")
                Switch(checked = true, onCheckedChange = {})
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Theme")
                Text("Light")
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text("Note creation",fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Create text notes by default")
                Switch(checked = false, onCheckedChange = {})
            }
            Text("You can always long press to create other note types")
            Spacer(modifier = Modifier)
            Text("Reminder defaults",fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Morning")
                Text("")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Afternoon")
                Text("")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Evening")
                Text("")
            }
            Spacer(modifier = Modifier)
            Text("Sharing",fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable sharing")
                Switch(checked = true, onCheckedChange = {})
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreenContent(modifier = Modifier, state = SettingsScreenUiState())
}