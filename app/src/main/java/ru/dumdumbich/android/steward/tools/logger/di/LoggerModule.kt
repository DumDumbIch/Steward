package ru.dumdumbich.android.steward.tools.logger.di

import org.koin.dsl.module
import ru.dumdumbich.android.steward.tools.logger.Logger
import ru.dumdumbich.android.steward.tools.logger.LoggerImpl

val loggerModule = module {
    factory<Logger> { params ->
        LoggerImpl(
            target = params.get<Any>(),
            isDebug = params.get<Boolean>()
        )
    }
}
