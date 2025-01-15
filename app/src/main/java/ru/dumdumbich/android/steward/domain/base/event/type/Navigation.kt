package ru.dumdumbich.android.steward.domain.base.event.type

import androidx.core.bundle.Bundle


sealed class Navigation : EventType() {
    data object Empty : Navigation()
    data class HomeScreen(val args: Bundle? = null) : Navigation()
    data class TuneScreen(val args: Bundle? = null) : Navigation()
    data class ToolsScreen(val args: Bundle? = null) : Navigation()
}
