package ru.dumdumbich.android.steward.ui.menu.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.dumdumbich.android.steward.domain.core.event.NavigationEventBus
import ru.dumdumbich.android.steward.ui.base.BaseViewModel


class MainMenuViewModel : BaseViewModel() {

    private val navigationEventBus: NavigationEventBus by inject()

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
                    navigationEventBus.onShowHomeScreen()
                }

                MainMenuViewEvent.OnPressToolsMenuItem -> {
                    navigationEventBus.onShowToolsScreen()
                }

                MainMenuViewEvent.OnPressTuneMenuItem -> {
                    navigationEventBus.onShowTuneScreen()
                }
            }
            clearEvent()
        }.launchIn(viewModelScope)
    }
}
