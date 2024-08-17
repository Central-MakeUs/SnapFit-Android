package memory.fabricators.snapfit.ui.start.di

import memory.fabricators.snapfit.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val startUiModule: Module
    get() = module {
        viewModel { StartViewModel(loginRepository = get()) }
    }
