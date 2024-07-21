package memory.fabricators.snapfit.database

import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module
    get() = module {
        includes()
    }
