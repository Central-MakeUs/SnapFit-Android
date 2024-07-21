package memory.fabricators.snapfit.datastore.auth.di

import memory.fabricators.snapfit.datastore.auth.AuthDataStoreDataSource
import memory.fabricators.snapfit.datastore.auth.AuthDataStoreDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val authDataStoreModule: Module
    get() = module {
        single<AuthDataStoreDataSource> { AuthDataStoreDataSourceImpl() }
    }
