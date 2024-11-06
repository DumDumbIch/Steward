package ru.dumdumbich.android.steward.ui.screen.main

internal sealed class MainViewEvent {
    data object Empty : MainViewEvent()
    data object OnClickMainButton : MainViewEvent()
}
