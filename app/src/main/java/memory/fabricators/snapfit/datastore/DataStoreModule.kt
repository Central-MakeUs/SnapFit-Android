package memory.fabricators.snapfit.datastore

import memory.fabricators.snapfit.datastore.user.di.userDataStoreModule
import org.koin.core.module.Module
import org.koin.dsl.module

val dataStoreModule: Module
    get() = module {
        includes(
            userDataStoreModule,
        )
    }
