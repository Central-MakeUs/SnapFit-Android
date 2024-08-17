package memory.fabricators.snapfit.core

import memory.fabricators.snapfit.core.network.di.networkCoreModule
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule: Module
    get() = module {
        includes(
            networkCoreModule,
        )
    }
