package com.example.composekeepnotescopy.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.KeyboardAlt
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composekeepnotescopy.ThemeColor

@Composable
internal fun SettingsScreen(modifier: Modifier) {
    val settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()
    val state by settingsScreenViewModel.state.collectAsStateWithLifecycle()

    SettingsScreenContent(
        modifier = modifier,
        state = state,
        onEvent = settingsScreenViewModel::onEvent
    )
}


@Composable
fun SettingsScreenContent(
    modifier: Modifier,
    state: SettingsScreenUiState,
    onEvent: (SettingsScreenViewModel.SettingsUiEvent) -> Unit
) {
    val timesOfDay = listOf(
        SettingsScreenViewModel.TimeOfDay.MORNING,
        SettingsScreenViewModel.TimeOfDay.AFTERNOON,
        SettingsScreenViewModel.TimeOfDay.EVENING
    )

    var selectedTimeOfDay by remember { mutableStateOf(timesOfDay[0]) }
    val shouldShowThemeColorDialog = remember { mutableStateOf(false) }
    val shouldShowInputPickerDialog = remember { mutableStateOf(false) }

    Surface(modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                "Display Options",
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.AddItemsToBottom(
                                !state.addItemsToBottomChecked
                            )
                        )
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Add new items to bottom")
                Switch(
                    checked = state.addItemsToBottomChecked,
                    onCheckedChange = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.AddItemsToBottom(
                                it
                            )
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.MoveCheckedItemsToBottom(
                                !state.moveCheckedItemsToBottomChecked
                            )
                        )
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Move checked items to bottom")
                Switch(
                    checked = state.moveCheckedItemsToBottomChecked,
                    onCheckedChange = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.MoveCheckedItemsToBottom(
                                it
                            )
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.DisplayRichLinkInPreview(
                                !state.displayRichLinkPreviewChecked
                            )
                        )
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Display rich link preview")
                Switch(
                    checked = state.displayRichLinkPreviewChecked,
                    onCheckedChange = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.DisplayRichLinkInPreview(
                                it
                            )
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable(onClick = {
                        shouldShowThemeColorDialog.value = true
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Theme")
                Text(
                    text = state.themeColor
                        ?: if (isSystemInDarkTheme()) ThemeColor.DARK.text else ThemeColor.LIGHT.text
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                "Note creation", fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.CreateTextNotesByDefault(
                                !state.createTextNotesByDefaultChecked
                            )
                        )
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Create text notes by default")
                Switch(
                    checked = state.createTextNotesByDefaultChecked,
                    onCheckedChange = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.CreateTextNotesByDefault(
                                it
                            )
                        )
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
                    .padding(top = 15.dp)
                    .clickable(onClick = {
                        selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.MORNING
                        shouldShowInputPickerDialog.value = true
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Morning")
                Text(
                    text = state.reminderMorningTime.toString(),
                    modifier = Modifier.clickable(onClick = {
                        selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.MORNING
                        shouldShowInputPickerDialog.value = true
                    })
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .clickable(
                        onClick = {
                            selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.AFTERNOON
                            shouldShowInputPickerDialog.value = true
                        }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Afternoon")
                Text(
                    text = state.reminderAfternoonTime.toString(),
                    modifier = Modifier.clickable(onClick = {
                        selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.AFTERNOON
                        shouldShowInputPickerDialog.value = true
                    })
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .clickable(onClick = {
                        selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.EVENING
                        shouldShowInputPickerDialog.value = true
                    }),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Evening")
                Text(
                    text = state.reminderEveningTime.toString(),
                    modifier = Modifier.clickable(onClick = {
                        selectedTimeOfDay = SettingsScreenViewModel.TimeOfDay.EVENING
                        shouldShowInputPickerDialog.value = true
                    })
                )
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
                    checked = state.enableSharingChecked,
                    onCheckedChange = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.EnableSharing(
                                it
                            )
                        )
                    }, colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue
                    )
                )
            }
        }
    }

    if (shouldShowThemeColorDialog.value) {
        ThemeSwitchDialog(state, onEvent, shouldShowThemeColorDialog)
    }

    if (shouldShowInputPickerDialog.value) {
        TimePickerDialog(
            onEvent,
            selectedTimeOfDay,
            when (selectedTimeOfDay) {
                SettingsScreenViewModel.TimeOfDay.MORNING -> state.reminderMorningTime
                SettingsScreenViewModel.TimeOfDay.AFTERNOON -> state.reminderAfternoonTime
                SettingsScreenViewModel.TimeOfDay.EVENING -> state.reminderEveningTime
            } ?: SettingsScreenViewModel.ReminderData(0, 0),
            shouldShowInputPickerDialog
        )
    }

}

@Composable
fun ThemeSwitchDialog(
    state: SettingsScreenUiState,
    onEvent: (SettingsScreenViewModel.SettingsUiEvent) -> Unit,
    onDismiss: MutableState<Boolean>
) {
    Dialog(onDismissRequest = { onDismiss.value = false }) {
        Card(modifier = Modifier) {
            Column(modifier = Modifier.padding(vertical = 15.dp)) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Choose theme",
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                onEvent.invoke(
                                    SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                        ThemeColor.LIGHT
                                    )
                                )
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.themeColor == ThemeColor.LIGHT.text,
                        onClick = {
                            onEvent.invoke(
                                SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                    ThemeColor.LIGHT
                                )
                            )
                        })
                    Text(text = "Light")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                onEvent.invoke(
                                    SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                        ThemeColor.DARK
                                    )
                                )
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.themeColor == ThemeColor.DARK.text,
                        onClick = {
                            onEvent.invoke(
                                SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                    ThemeColor.DARK
                                )
                            )
                        })
                    Text(text = "Dark")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                onEvent.invoke(
                                    SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                        ThemeColor.SYSTEM_DEFAULT
                                    )
                                )
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.themeColor == ThemeColor.SYSTEM_DEFAULT.text,
                        onClick = {
                            onEvent.invoke(
                                SettingsScreenViewModel.SettingsUiEvent.ThemeColor(
                                    ThemeColor.SYSTEM_DEFAULT
                                )
                            )
                        })
                    Text(text = "System default")
                }
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 15.dp),
                    text = "To change the theme of your Keep widget and notifications, manage you Android screen and display settings."
                )
                Text(
                    text = "Cancel", modifier = Modifier
                        .clickable(onClick = { onDismiss.value = false })
                        .align(Alignment.End)
                        .padding(end = 25.dp, top = 15.dp, bottom = 5.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onEvent: (SettingsScreenViewModel.SettingsUiEvent) -> Unit,
    timeOfDay: SettingsScreenViewModel.TimeOfDay,
    reminderData: SettingsScreenViewModel.ReminderData,
    onDismiss: MutableState<Boolean>
) {
    val isTimePickerShown = remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = reminderData.hour,
        initialMinute = reminderData.minute,
        is24Hour = true,
    )

    Dialog(onDismissRequest = { onDismiss.value = false }) {
        Card() {
            Column(modifier = Modifier.padding(15.dp)) {
                Text("Select time", modifier = Modifier.padding(bottom = 15.dp))
                if (!isTimePickerShown.value) {
                    TimeInput(state = timePickerState)
                } else {
                    TimePicker(state = timePickerState)
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (!isTimePickerShown.value) {
                        Icon(
                            imageVector = Icons.Outlined.AccessTime, "Input Picker Icon",
                            modifier = Modifier.clickable(onClick = {
                                isTimePickerShown.value = true
                            })
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardAlt, "Time Picker Icon",
                            modifier = Modifier.clickable(onClick = {
                                isTimePickerShown.value = false
                            })
                        )
                    }
                    Text(
                        "Cancel", modifier = Modifier
                            .clickable(onClick = { onDismiss.value = false })
                            .padding(start = 25.dp)
                    )
                    Text("OK", modifier = Modifier.clickable(onClick = {
                        onEvent.invoke(
                            SettingsScreenViewModel.SettingsUiEvent.ReminderTimes(
                                timeOfDay = timeOfDay,
                                reminderData = SettingsScreenViewModel.ReminderData(
                                    hour = timePickerState.hour,
                                    minute = timePickerState.minute
                                )
                            )
                        )
                        onDismiss.value = false
                    }))
                }
            }
        }
    }
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialog(
        onEvent = {},
        timeOfDay = SettingsScreenViewModel.TimeOfDay.MORNING,
        reminderData = SettingsScreenViewModel.ReminderData(8, 0),
        onDismiss = mutableStateOf(true)
    )
}

@Preview
@Composable
fun ThemeSwitchDialogPreview() {
    ThemeSwitchDialog(
        state = SettingsScreenUiState(),
        onEvent = {},
        onDismiss = mutableStateOf(true)
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreenContent(modifier = Modifier, state = SettingsScreenUiState(), onEvent = {})
}