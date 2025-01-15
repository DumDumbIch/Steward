package ru.dumdumbich.android.steward.tools.logger

import timber.log.Timber


class LoggerImpl(
    val target: Any,
    val isDebug: Boolean
) : Logger {

    private val key: String = "@@@"
    private val tag = "$key [${Thread.currentThread().name}] ${target.javaClass.simpleName} : ${target.hashCode()}"

    override fun toConsole(message: String) {
        if (isDebug) Timber.tag(tag).d(message)
    }
}
