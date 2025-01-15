package ru.dumdumbich.android.steward.di

import android.content.Context
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.dumdumbich.android.steward.domain.core.event.NavigationEventBus
import ru.dumdumbich.android.steward.domain.core.event.SmsEventBus
import ru.dumdumbich.android.steward.domain.usecase.navigation.NavigationEventUseCase
import ru.dumdumbich.android.steward.domain.usecase.sms.ReceivedSmsEventsUseCase
import ru.dumdumbich.android.steward.server.iot.IotServer
import ru.dumdumbich.android.steward.ui.core.toast.CenterToaster
import ru.dumdumbich.android.steward.ui.core.toast.SimpleToaster
import ru.dumdumbich.android.steward.ui.menu.main.MainMenuViewModel
import ru.dumdumbich.android.steward.ui.navigation.MainActivityNavigator
import ru.dumdumbich.android.steward.ui.navigation.MainNavigator
import ru.dumdumbich.android.steward.ui.screen.main.MainViewModel


val appModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        MainMenuViewModel()
    }
    single {
        SimpleToaster(
            context = get<Context>()
        )
    }
    single {
        CenterToaster(
            context = get<Context>()
        )
    }
    single {
        IotServer
    }
    single {
        SmsEventBus()
    }
    factory {
        ReceivedSmsEventsUseCase(
            eventBus = get<SmsEventBus>()
        )
    }
    single {
        NavigationEventBus()
    }
    factory {
        NavigationEventUseCase(
            eventBus = get<NavigationEventBus>()
        )
    }
    single {
        MainNavigator()
    }
    single {
        MainActivityNavigator()
    }
}
