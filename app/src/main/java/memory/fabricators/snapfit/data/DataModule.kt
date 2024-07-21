package memory.fabricators.snapfit.data

import memory.fabricators.snapfit.data.auth.di.authDataModule
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module
    get() = module {
        includes(
            authDataModule,
        )
    }
