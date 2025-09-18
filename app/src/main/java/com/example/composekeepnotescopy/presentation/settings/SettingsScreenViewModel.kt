package com.example.composekeepnotescopy.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composekeepnotescopy.ThemeColor
import com.example.composekeepnotescopy.domain.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenUiState())
    val state = _state.asStateFlow()

    init {
        getAddItemsToBottom()
        getMoveCheckedItemsToBottom()
        getDisplayRichLinkInPreview()
        getCreateTextNotesByDefault()
        getEnableSharing()
        getThemeColor()
        getReminderMorning()
        getReminderAfternoon()
        getReminderEvening()
    }

    fun onEvent(event: SettingsUiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is SettingsUiEvent.AddItemsToBottom -> {
                    dataStoreRepository.setAddItemsToBottomCheckedState(event.value)
                }

                is SettingsUiEvent.MoveCheckedItemsToBottom -> {
                    dataStoreRepository.setMoveCheckedItemsToBottomCheckedState(event.value)
                }

                is SettingsUiEvent.DisplayRichLinkInPreview -> {
                    dataStoreRepository.setDisplayRichLinkPreviewCheckedState(event.value)
                }

                is SettingsUiEvent.CreateTextNotesByDefault -> {
                    dataStoreRepository.setCreateTextNotesByDefaultCheckedState(event.value)
                }

                is SettingsUiEvent.EnableSharing -> {
                    dataStoreRepository.setEnableSharingCheckedState(event.value)
                }

                is SettingsUiEvent.ThemeColor -> {
                    dataStoreRepository.setThemeColor(event.value.text)
                }

                is SettingsUiEvent.ReminderTimes -> {
                    when (event.timeOfDay) {
                        TimeOfDay.MORNING -> {
                            dataStoreRepository.setReminderMorning(value = event.reminderData.toString())
                        }
                        TimeOfDay.AFTERNOON -> {
                            dataStoreRepository.setReminderAfternoon(value = event.reminderData.toString())
                        }
                        TimeOfDay.EVENING -> {
                            dataStoreRepository.setReminderEvening(value = event.reminderData.toString())
                        }
                    }
                }
            }
        }
    }

    private fun getAddItemsToBottom() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getAddItemsToBottomCheckedState().collect {
                _state.value = _state.value.copy(addItemsToBottomChecked = it ?: false)
            }
        }
    }

    private fun getMoveCheckedItemsToBottom() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getMoveCheckedItemsToBottomCheckedState().collect {
                _state.value = _state.value.copy(moveCheckedItemsToBottomChecked = it ?: false)
            }
        }
    }

    private fun getDisplayRichLinkInPreview() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getDisplayRichLinkPreviewCheckedState().collect {
                _state.value = _state.value.copy(displayRichLinkPreviewChecked = it ?: false)
            }
        }
    }

    private fun getCreateTextNotesByDefault() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getCreateTextNotesByDefaultCheckedState().collect {
                _state.value = _state.value.copy(createTextNotesByDefaultChecked = it ?: false)
            }
        }
    }

    private fun getEnableSharing() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getEnableSharingCheckedState().collect {
                _state.value = _state.value.copy(enableSharingChecked = it ?: false)
            }
        }
    }

    private fun getThemeColor() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getThemeColor().collect {
                _state.value = _state.value.copy(themeColor = it ?: ThemeColor.LIGHT.text)
            }
        }
    }

    private fun getReminderMorning() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getReminderMorning().collect { morningReminderTime ->
                _state.value = _state.value.copy(reminderMorningTime = timeOfDayConverter(morningReminderTime, TimeOfDay.MORNING))
            }
        }
    }

    private fun getReminderAfternoon() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getReminderAfternoon().collect { afternoonReminderTime ->
                _state.value = _state.value.copy(reminderAfternoonTime = timeOfDayConverter(afternoonReminderTime, TimeOfDay.AFTERNOON))
            }
        }
    }

    private fun getReminderEvening() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getReminderEvening().collect { eveningReminderTime ->
                _state.value = _state.value.copy(reminderEveningTime = timeOfDayConverter(eveningReminderTime, TimeOfDay.EVENING))
            }
        }
    }

    private fun timeOfDayConverter(time: String?, timeOfDay: TimeOfDay): ReminderData {
        return time?.let {
            val time = time.split(":")
            val hour =  time[0].toInt()
            val minute = time[1].toInt()

            ReminderData(hour, minute)
        } ?: run {
            when (timeOfDay) {
                TimeOfDay.MORNING -> ReminderData(7, 0)
                TimeOfDay.AFTERNOON -> ReminderData(13, 0)
                TimeOfDay.EVENING -> ReminderData(18, 0)
            }
        }
    }

    sealed interface SettingsUiEvent {
        data class AddItemsToBottom(val value: Boolean) : SettingsUiEvent
        data class MoveCheckedItemsToBottom(val value: Boolean) : SettingsUiEvent
        data class DisplayRichLinkInPreview(val value: Boolean) : SettingsUiEvent
        data class CreateTextNotesByDefault(val value: Boolean) : SettingsUiEvent
        data class EnableSharing(val value: Boolean) : SettingsUiEvent
        data class ThemeColor(val value: com.example.composekeepnotescopy.ThemeColor) : SettingsUiEvent
        data class ReminderTimes(val timeOfDay: TimeOfDay, val reminderData: ReminderData) : SettingsUiEvent
    }

    enum class TimeOfDay {
        MORNING,
        AFTERNOON,
        EVENING
    }

    data class ReminderData(
        val hour: Int,
        val minute: Int,
    ) {
        override fun toString(): String {
            val hour = if (hour < 10) "0$hour" else hour
            val minute = if (minute < 10) "0$minute" else minute

            return "$hour:$minute"
        }

    }
}