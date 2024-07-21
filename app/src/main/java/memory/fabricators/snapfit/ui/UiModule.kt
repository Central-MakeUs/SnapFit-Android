package memory.fabricators.snapfit.ui

import memory.fabricators.snapfit.ui.signup.di.signUpUiModule
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModule: Module
    get() = module {
        includes(
            signUpUiModule,
        )
    }
