package memory.fabricators.snapfit.ui.main.home.di

import memory.fabricators.snapfit.ui.main.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainHomeUiModule: Module
    get() = module {
        viewModel { HomeViewModel(userRepository = get()) }
    }
