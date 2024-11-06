package ru.dumdumbich.android.steward.ui.screen.main

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

internal class MainViewModel : BaseViewModel() {

    private val navigator: Navigation by inject()
    private val navigationEvents = listOf(
        NavigationEvent.ToHomeScreen,
        NavigationEvent.ToTuneScreen,
        NavigationEvent.ToToolsScreen
    )
    private var currentNavigatorEventsPosition: Int = 0

    private var _viewEvent:
            MutableStateFlow<MainViewEvent> = MutableStateFlow(MainViewEvent.Empty)
    private val viewEvent: StateFlow<MainViewEvent> = _viewEvent.asStateFlow()

    private var _viewAction:
            MutableStateFlow<MainViewAction> = MutableStateFlow(MainViewAction.Idle)
    val viewAction: StateFlow<MainViewAction> = _viewAction.asStateFlow()

    private lateinit var viewState: MainViewState

    init {
        handlerEvent()
    }

    fun getViewStateFromUi(viewState: MainViewState) {
        logger.toConsole("viewState = $viewState")
        this.viewState = viewState
    }

    fun onClickMainButton() {
        viewModelScope.launch {
            _viewEvent.emit(MainViewEvent.OnClickMainButton)
        }
    }

    private fun showProgress() {
        viewModelScope.launch {
            _viewAction.emit(MainViewAction.ShowProgress)
        }
    }

    private fun hideProgress() {
        viewModelScope.launch {
            _viewAction.emit(MainViewAction.HideProgress)
        }
    }

    private fun switchProgress() {
        viewModelScope.launch {
            _viewAction.emit(MainViewAction.SwitchProgress)
        }
    }

    private fun switchScene() {
        viewModelScope.launch {
            if (currentNavigatorEventsPosition < navigationEvents.lastIndex) {
                currentNavigatorEventsPosition += 1
            } else {
                currentNavigatorEventsPosition = 0
            }
            navigator.putEvent(navigationEvents[currentNavigatorEventsPosition])
        }
    }

    private fun clearEvent() {
        viewModelScope.launch {
            _viewEvent.emit(MainViewEvent.Empty)
        }
    }

    fun clearAction() {
        viewModelScope.launch {
            _viewAction.emit(MainViewAction.Idle)
        }
    }

    private fun handlerEvent() {
        viewEvent.onEach { event ->
            logger.toConsole("Event = $event")
            when (event) {
                MainViewEvent.Empty -> Unit
                MainViewEvent.OnClickMainButton -> {
                    //switchProgress()
                    switchScene()
                }
            }
            clearEvent()
        }.launchIn(viewModelScope)
    }
}
