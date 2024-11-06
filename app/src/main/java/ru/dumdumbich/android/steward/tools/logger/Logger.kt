package ru.dumdumbich.android.steward.tools.logger


/**
 * Logger
 *
 * ```kotlin
 *
 * class App : Application() {
 *
 *     override fun onCreate() {
 *         super.onCreate()
 *         initLogger()
 *         ...
 *     }
 *     ...
 *
 *     private fun initLogger() {
 *         Timber.plant(Timber.DebugTree())
 *     }
 * }
 * ```
 *
 */
interface Logger {

    fun toConsole(message: String)
}
