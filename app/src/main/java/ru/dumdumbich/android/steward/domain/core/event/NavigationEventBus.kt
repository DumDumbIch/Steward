package ru.dumdumbich.android.steward.domain.core.event

import androidx.core.bundle.Bundle
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dumdumbich.android.steward.domain.base.event.BaseEventBus
import ru.dumdumbich.android.steward.domain.base.event.type.Navigation


class NavigationEventBus : BaseEventBus<Navigation>() {

    init {
        init(Navigation.Empty)
    }

    fun onShowHomeScreen(args: Bundle? = null) {
        eventBusScope.launch {
            event.update { Navigation.HomeScreen(args) }
        }
    }

    fun onShowTuneScreen(args: Bundle? = null) {
        eventBusScope.launch {
            event.update { Navigation.TuneScreen(args) }
        }
    }

    fun onShowToolsScreen(args: Bundle? = null) {
        eventBusScope.launch {
            event.update { Navigation.ToolsScreen(args) }
        }
    }
}
