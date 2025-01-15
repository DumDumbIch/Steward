package ru.dumdumbich.android.steward.ui.screen.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.dumdumbich.android.steward.ui.base.BaseViewModel

internal class MainViewModel : BaseViewModel() {

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
                MainViewEvent.OnClickMainButton -> {}
            }
            clearEvent()
        }.launchIn(viewModelScope)
    }
}
