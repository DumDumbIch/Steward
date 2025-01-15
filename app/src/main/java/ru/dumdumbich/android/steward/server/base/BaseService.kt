package ru.dumdumbich.android.steward.server.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger


abstract class BaseService : Service(), KoinComponent {

    protected val serviceScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    protected val logger: Logger by inject { parametersOf(this, true) }

    private var _binder: IBinder? = null
    private val binder get() = _binder!!

    protected var startMode: Int = 0
    private var allowRebind: Boolean = false


    override fun onCreate() {
        logger.toConsole("Service.onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logger.toConsole(
            "Service.onStartCommand() : intent = $intent, flags = $flags, startId = $startId"
        )
        return startMode
    }

    override fun onBind(intent: Intent?): IBinder? {
        logger.toConsole("Service.onBind() : intent = $intent")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logger.toConsole("Service.onUnbind() : intent = $intent")
        return allowRebind
    }

    override fun onRebind(intent: Intent?) {
        logger.toConsole("Service.onRebind() : intent = $intent")
    }

    override fun onDestroy() {
        logger.toConsole("Service.onDestroy()")
    }
}
