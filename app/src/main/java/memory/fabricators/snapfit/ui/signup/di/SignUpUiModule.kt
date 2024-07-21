package memory.fabricators.snapfit.ui.signup.di

import memory.fabricators.snapfit.ui.signup.SignUpViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val signUpUiModule: Module
    get() = module {
        singleOf(::SignUpViewModel)
    }
