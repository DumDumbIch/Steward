package ru.dumdumbich.android.steward.ui.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.koin.android.ext.android.inject
import ru.dumdumbich.android.steward.databinding.ActivityMainBinding
import ru.dumdumbich.android.steward.server.iot.IotServer
import ru.dumdumbich.android.steward.ui.base.BaseActivity
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.navigation.ScenarioMainActivity

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val server: IotServer by inject()

    private val mainMenu: Scene = ScenarioMainActivity.MainMenu().scene
    private val mainScene: Scene = ScenarioMainActivity.MainScene().scene

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(ui.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainMenu.install(supportFragmentManager, ui.menuContainer.id)
        mainScene.install(supportFragmentManager, ui.sceneContainer.id)

        ui.mainLayout.apply {
            setOnClickListener {
                logger.toConsole("ui.mainLayout.setOnClickListener")
                mainMenu.hide()
            }
            setOnLongClickListener {
                logger.toConsole("ui.mainLayout.setOnLongClickListener")
                mainMenu.show()
                true
            }
        }
        server.start()
    }

    override fun onResume() {
        super.onResume()
        mainMenu.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stop()
    }
}
