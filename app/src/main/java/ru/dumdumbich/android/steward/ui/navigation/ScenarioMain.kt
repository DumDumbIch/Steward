package ru.dumdumbich.android.steward.ui.navigation

import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.screen.home.HomeFragment
import ru.dumdumbich.android.steward.ui.screen.tools.ToolsFragment
import ru.dumdumbich.android.steward.ui.screen.tune.TuneFragment

internal sealed class ScenarioMain : Scenario() {
    data class HomeScene(
        val scene: Scene = Scene(
            HomeFragment::class.java,
            tag = "SCENE_HOME"
        )
    ) : ScenarioMain()

    data class TuneScene(
        val scene: Scene = Scene(
            TuneFragment::class.java,
            tag = "SCENE_TUNE"
        )
    ) : ScenarioMain()

    data class ToolsScene(
        val scene: Scene = Scene(
            ToolsFragment::class.java,
            tag = "SCENE_TOOLS"
        )
    ) : ScenarioMain()
}
