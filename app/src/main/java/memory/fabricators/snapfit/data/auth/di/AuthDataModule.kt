package memory.fabricators.snapfit.data.auth.di

import memory.fabricators.snapfit.data.auth.AuthRepository
import memory.fabricators.snapfit.data.auth.AuthRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val authDataModule: Module
    get() = module {
        single<AuthRepository> {
            AuthRepositoryImpl(
                authDataStoreDataSource = get(),
                authNetworkDataSource = get(),
            )
        }
    }
