package ru.dumdumbich.android.steward.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.dumdumbich.android.steward.ui.menu.main.MainMenuViewModel
import ru.dumdumbich.android.steward.ui.navigation.Navigation
import ru.dumdumbich.android.steward.ui.screen.main.MainViewModel

val appModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        MainMenuViewModel()
    }
    single {
        Navigation
    }
}
