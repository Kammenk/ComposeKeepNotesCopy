package com.example.composekeepnotescopy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composekeepnotescopy.domain.MainActivityRepository
import com.example.composekeepnotescopy.presentation.settings.SettingsScreenViewModel.ReminderData
import com.example.composekeepnotescopy.presentation.settings.SettingsScreenViewModel.SettingsUiEvent
import com.example.composekeepnotescopy.presentation.settings.SettingsScreenViewModel.TimeOfDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val mainActivityRepository: MainActivityRepository): ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    init {
        isDarkTheme()
        getListViewState()
    }

    fun onEvent(event: MainActivityUiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when(event) {
                is MainActivityUiEvent.SetListViewState -> {
                    mainActivityRepository.setListViewState(event.isGrid)
                }
            }
        }
    }

    fun isDarkTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            mainActivityRepository.getThemeColor().collect { themeColor ->
                _state.value = _state.value.copy(isDarkTheme = themeColor)
            }
        }
    }

    fun getListViewState() {
        viewModelScope.launch(Dispatchers.IO) {
            mainActivityRepository.getListViewState().collect { listViewState ->
                _state.value = _state.value.copy(isGridListView = listViewState == true)
            }
        }
    }

    sealed interface MainActivityUiEvent {
        data class SetListViewState(val isGrid: Boolean) : MainActivityUiEvent
    }
}