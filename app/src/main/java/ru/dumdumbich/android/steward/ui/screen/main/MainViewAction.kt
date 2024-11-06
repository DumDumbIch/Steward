package ru.dumdumbich.android.steward.ui.screen.main

internal sealed class MainViewAction {
    data object Idle : MainViewAction()
    data object ShowProgress : MainViewAction()
    data object HideProgress : MainViewAction()
    data object SwitchProgress : MainViewAction()
    data class PutViewState(val viewState: MainViewState) : MainViewAction()
}
