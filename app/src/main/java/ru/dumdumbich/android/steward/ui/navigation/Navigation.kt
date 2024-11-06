package ru.dumdumbich.android.steward.ui.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.dumdumbich.android.steward.ui.base.BaseNavigation

internal object Navigation : BaseNavigation() {

    private val navigationScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )

    private var _navigationEvent:
            MutableStateFlow<NavigationEvent> = MutableStateFlow(NavigationEvent.Empty)
    private val navigationEvent: StateFlow<NavigationEvent> = _navigationEvent.asStateFlow()

    private var _navigationAction:
            MutableStateFlow<NavigationAction> = MutableStateFlow(NavigationAction.Idle)
    val navigationAction: StateFlow<NavigationAction> = _navigationAction.asStateFlow()

    init {
        handlerEvent()
    }

    private fun putAction(action: NavigationAction) {
        navigationScope.launch {
            _navigationAction.emit(action)
        }
    }

    fun clearAction() {
        putAction(NavigationAction.Idle)
    }

    fun putEvent(event: NavigationEvent) {
        navigationScope.launch {
            _navigationEvent.emit(event)
        }
    }

    private fun clearEvent() {
        putEvent(NavigationEvent.Empty)
    }

    private fun handlerEvent() {
        navigationEvent.onEach { event ->
            logger.toConsole("Navigation Event = $event")
            when (event) {
                NavigationEvent.Empty -> Unit
                NavigationEvent.ToHomeScreen -> putAction(NavigationAction.ShowHomeScene())
                NavigationEvent.ToToolsScreen -> putAction(NavigationAction.ShowToolsScene())
                NavigationEvent.ToTuneScreen -> putAction(NavigationAction.ShowTuneScene())
            }
            clearEvent()
        }.launchIn(navigationScope)
    }
}
