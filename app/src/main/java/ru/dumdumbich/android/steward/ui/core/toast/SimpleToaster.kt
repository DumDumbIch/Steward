package ru.dumdumbich.android.steward.ui.core.toast

import android.content.Context
import android.widget.Toast
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger
import java.lang.System.currentTimeMillis


class SimpleToaster(
    private val context: Context
) : KoinComponent {

    private val logger: Logger by inject { parametersOf(this, true) }
    private var previousMessage: String = ""
    private var lastShowTime: Long = getCurrentTime()

    fun show(message: String) {
        logger.toConsole(message)
//        if (message.isNotBlank() && message != previousMessage) {
//            val currentTime = getCurrentTime()
//            if (REPEATED_SHOW_PROTECTION_TIME_MS + lastShowTime < currentTime) {
        val toast = Toast(context)
        toast.setText(message)
        previousMessage = message
        lastShowTime = getCurrentTime()
        toast.show()
//            }
//        }
    }

    private fun getCurrentTime(): Long = currentTimeMillis()

    companion object {
        private const val REPEATED_SHOW_PROTECTION_TIME_MS = 5_000L
    }
}
