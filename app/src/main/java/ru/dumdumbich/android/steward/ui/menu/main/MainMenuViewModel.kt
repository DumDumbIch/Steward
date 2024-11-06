package ru.dumdumbich.android.steward.ui.menu.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.dumdumbich.android.steward.ui.base.BaseViewModel
import ru.dumdumbich.android.steward.ui.navigation.Navigation
import ru.dumdumbich.android.steward.ui.navigation.NavigationEvent

class MainMenuViewModel : BaseViewModel() {

    private val navigator: Navigation by inject()

    private var _viewEvent:
            MutableStateFlow<MainMenuViewEvent> = MutableStateFlow(MainMenuViewEvent.Empty)
    private val viewEvent: StateFlow<MainMenuViewEvent> = _viewEvent.asStateFlow()


    init {
        handlerEvent()
    }

    fun onPressHomeMenuItem() {
        viewModelScope.launch {
            _viewEvent.emit(MainMenuViewEvent.OnPressHomeMenuItem)
        }
    }

    fun onPressTuneMenuItem() {
        viewModelScope.launch {
            _viewEvent.emit(MainMenuViewEvent.OnPressTuneMenuItem)
        }
    }

    fun onPressToolsMenuItem() {
        viewModelScope.launch {
            _viewEvent.emit(MainMenuViewEvent.OnPressToolsMenuItem)
        }
    }

    private fun clearEvent() {
        viewModelScope.launch {
            _viewEvent.emit(MainMenuViewEvent.Empty)
        }
    }

    private fun handlerEvent() {
        viewEvent.onEach { event ->
            logger.toConsole("Event = $event")
            when (event) {
                MainMenuViewEvent.Empty -> Unit

                MainMenuViewEvent.OnPressHomeMenuItem -> {
                    navigator.putEvent(NavigationEvent.ToHomeScreen)
                }

                MainMenuViewEvent.OnPressToolsMenuItem -> {
                    navigator.putEvent(NavigationEvent.ToToolsScreen)
                }

                MainMenuViewEvent.OnPressTuneMenuItem -> {
                    navigator.putEvent(NavigationEvent.ToTuneScreen)
                }
            }
            clearEvent()
        }.launchIn(viewModelScope)
    }
}
