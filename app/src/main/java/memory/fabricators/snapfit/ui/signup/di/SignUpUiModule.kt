package memory.fabricators.snapfit.ui.signup.di

import memory.fabricators.snapfit.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val signUpUiModule: Module
    get() = module {
        viewModel { SignUpViewModel(userRepository = get()) }
    }
