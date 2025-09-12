package com.example.composekeepnotescopy.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

                is SettingsUiEvent.ShowThemeChangeDialog -> {
                    _state.value = _state.value.copy(showThemeChangeDialog = true)
                }

                is SettingsUiEvent.ThemeColor -> {
                    dataStoreRepository.setThemeColor(event.value.text)
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
                _state.value = _state.value.copy(themeColor = it ?: "Light")
            }
        }
    }

    sealed interface SettingsUiEvent {
        data class AddItemsToBottom(val value: Boolean) : SettingsUiEvent
        data class MoveCheckedItemsToBottom(val value: Boolean) : SettingsUiEvent
        data class DisplayRichLinkInPreview(val value: Boolean) : SettingsUiEvent
        data class CreateTextNotesByDefault(val value: Boolean) : SettingsUiEvent
        data class EnableSharing(val value: Boolean) : SettingsUiEvent
        data object ShowThemeChangeDialog : SettingsUiEvent
        data class ThemeColor(val value: com.example.composekeepnotescopy.ThemeColor) : SettingsUiEvent
    }
}