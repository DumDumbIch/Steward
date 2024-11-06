package ru.dumdumbich.android.steward.ui.navigation

import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.menu.main.MainMenuFragment
import ru.dumdumbich.android.steward.ui.screen.main.MainFragment

internal sealed class ScenarioMainActivity : Scenario() {
    data class MainMenu(
        val scene: Scene = Scene(
            MainMenuFragment::class.java,
            tag = "MENU_MAIN"
        )
    ) : ScenarioMainActivity()

    data class MainScene(
        val scene: Scene = Scene(
            MainFragment::class.java,
            tag = "SCENE_MAIN"
        )
    ) : ScenarioMainActivity()
}
