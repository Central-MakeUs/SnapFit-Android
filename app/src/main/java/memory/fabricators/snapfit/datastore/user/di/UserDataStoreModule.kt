package memory.fabricators.snapfit.datastore.user.di

import memory.fabricators.snapfit.datastore.user.UserDataStoreDataSource
import memory.fabricators.snapfit.datastore.user.UserDataStoreDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val userDataStoreModule: Module
    get() = module {
        single<UserDataStoreDataSource> { UserDataStoreDataSourceImpl() }
    }
