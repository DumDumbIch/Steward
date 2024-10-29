package ru.dumdumbich.android.steward.tools.logger

import timber.log.Timber

// вместо isDebug = true/false передавать значение BuildConfig.Debug - уточнить как это правильно сделать
class LoggerImpl(
    val target: Any,
    val isDebug: Boolean
) : Logger {

    private val key: String = "@@@"
    private val tag = "$key ${target.javaClass.simpleName} : ${target.hashCode()}"

    init {
        Timber.plant(Timber.DebugTree())
    }

    override fun toConsole(message: String) {
        if (isDebug) Timber.tag(tag).d(message)
    }
}
