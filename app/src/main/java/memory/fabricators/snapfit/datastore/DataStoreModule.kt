package memory.fabricators.snapfit.datastore

import memory.fabricators.snapfit.datastore.auth.di.authDataStoreModule
import org.koin.core.module.Module
import org.koin.dsl.module

val dataStoreModule: Module
    get() = module {
        includes(
            authDataStoreModule,
        )
    }
