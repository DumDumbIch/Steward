package ru.dumdumbich.android.steward

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.dumdumbich.android.steward.di.appModule
import ru.dumdumbich.android.steward.tools.logger.di.loggerModule
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, loggerModule))
        }
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }
}
