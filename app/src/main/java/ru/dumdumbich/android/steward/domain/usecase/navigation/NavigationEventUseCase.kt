package ru.dumdumbich.android.steward.domain.usecase.navigation

import androidx.core.bundle.Bundle
import ru.dumdumbich.android.steward.domain.base.event.type.Navigation
import ru.dumdumbich.android.steward.domain.core.event.NavigationEventBus


class NavigationEventUseCase(private val eventBus: NavigationEventBus) {

    operator fun invoke(
        navigateEmpty: () -> Unit,
        navigateToHomeScreen: (Bundle?) -> Unit,
        navigateToTuneScreen: (Bundle?) -> Unit,
        navigateToToolsScreen: (Bundle?) -> Unit,
    ) {
        eventBus.eventsHandler { event ->
            when (event) {
                is Navigation.Empty -> navigateEmpty()
                is Navigation.HomeScreen -> navigateToHomeScreen(event.args)
                is Navigation.TuneScreen -> navigateToTuneScreen(event.args)
                is Navigation.ToolsScreen -> navigateToToolsScreen(event.args)
            }
        }
    }
}
