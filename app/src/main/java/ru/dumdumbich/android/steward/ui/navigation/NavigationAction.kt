package ru.dumdumbich.android.steward.ui.navigation

import ru.dumdumbich.android.steward.ui.core.scene.Scene

internal sealed class NavigationAction {
    open lateinit var scene: Scene

    data object Idle : NavigationAction()

    data class ShowHomeScene(
        override var scene: Scene = ScenarioMain.HomeScene().scene
    ) : NavigationAction()

    data class ShowTuneScene(
        override var scene: Scene = ScenarioMain.TuneScene().scene
    ) : NavigationAction()

    data class ShowToolsScene(
        override var scene: Scene = ScenarioMain.ToolsScene().scene
    ) : NavigationAction()
}
