package ru.dumdumbich.android.steward.ui.screen

import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.koin.android.ext.android.inject
import ru.dumdumbich.android.steward.data.core.mobile.sms.SmsReceiver
import ru.dumdumbich.android.steward.databinding.ActivityMainBinding
import ru.dumdumbich.android.steward.server.iot.IotServer
import ru.dumdumbich.android.steward.ui.base.BaseActivity
import ru.dumdumbich.android.steward.ui.core.dialog.ConfirmationDialogFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.navigation.MainActivityNavigator


class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val server: IotServer by inject()
    private val smsReceiver: SmsReceiver = SmsReceiver()

    private val navigator: MainActivityNavigator by inject()
    private lateinit var mainMenu: Scene
    private lateinit var mainScene: Scene
    private val confirmationDialog: ConfirmationDialogFragment = ConfirmationDialogFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(ui.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.SEND_SMS,
                    android.Manifest.permission.READ_SMS
                ),
                PackageManager.PERMISSION_GRANTED
            )
        }
        registerReceiver(
            smsReceiver,
            IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        )

        mainMenu = navigator.createMainMenu()
        mainMenu.install(supportFragmentManager, ui.menuContainer.id)

        mainScene = navigator.createMainScene()
        mainScene.install(supportFragmentManager, ui.sceneContainer.id)

        ui.mainLayout.apply {
            setOnClickListener {
//                centerToaster.show("Simple toaster from MainActivity")
//                simpleSnack.show("Simple snack from MainActivity")
                confirmationDialog.show(supportFragmentManager, ConfirmationDialogFragment.TAG)
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
        unregisterReceiver(smsReceiver)
        server.stop()
    }
}
