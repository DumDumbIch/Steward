package ru.dumdumbich.android.steward.ui.navigation

import androidx.core.bundle.Bundle
import ru.dumdumbich.android.steward.ui.base.BaseNavigator
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.menu.main.MainMenuFragment
import ru.dumdumbich.android.steward.ui.screen.main.MainFragment


class MainActivityNavigator : BaseNavigator() {

    fun createMainMenu(args: Bundle? = null): Scene {
        return MainMenuFragment.createScene(args)
    }

    fun createMainScene(args: Bundle? = null): Scene {
        return MainFragment.createScene(args)
    }
}
