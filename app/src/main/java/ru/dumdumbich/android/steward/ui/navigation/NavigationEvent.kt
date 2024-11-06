package ru.dumdumbich.android.steward.ui.navigation

internal sealed class NavigationEvent {
    data object Empty : NavigationEvent()
    data object ToHomeScreen : NavigationEvent()
    data object ToTuneScreen : NavigationEvent()
    data object ToToolsScreen : NavigationEvent()
}
