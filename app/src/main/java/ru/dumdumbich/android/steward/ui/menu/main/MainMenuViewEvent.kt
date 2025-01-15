package ru.dumdumbich.android.steward.ui.menu.main


internal sealed class MainMenuViewEvent {
    data object Empty : MainMenuViewEvent()
    data object OnPressHomeMenuItem : MainMenuViewEvent()
    data object OnPressTuneMenuItem : MainMenuViewEvent()
    data object OnPressToolsMenuItem : MainMenuViewEvent()
}
