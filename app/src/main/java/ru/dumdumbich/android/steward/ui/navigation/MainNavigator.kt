package ru.dumdumbich.android.steward.ui.navigation

import androidx.core.bundle.Bundle
import ru.dumdumbich.android.steward.ui.base.BaseNavigator
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.screen.home.HomeFragment
import ru.dumdumbich.android.steward.ui.screen.tools.ToolsFragment
import ru.dumdumbich.android.steward.ui.screen.tune.TuneFragment


class MainNavigator : BaseNavigator() {

    fun createHomeScene(args: Bundle? = null): Scene {
        return HomeFragment.createScene(args)
    }

    fun createTuneScene(args: Bundle? = null): Scene {
        return TuneFragment.createScene(args)
    }

    fun createToolsScene(args: Bundle? = null): Scene {
        return ToolsFragment.createScene(args)
    }
}
