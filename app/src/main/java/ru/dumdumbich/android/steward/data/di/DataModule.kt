package ru.dumdumbich.android.steward.data.di

import org.koin.dsl.module
import ru.dumdumbich.android.steward.data.core.mobile.sms.SmsSender
import ru.dumdumbich.android.steward.data.core.mobile.sms.SmsSenderImpl


val dataModule = module {
    single<SmsSender> {
        SmsSenderImpl()
    }
}
