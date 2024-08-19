package memory.fabricators.snapfit.data.user.di

import memory.fabricators.snapfit.data.user.UserRepository
import memory.fabricators.snapfit.data.user.UserRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val userDataModule: Module
    get() = module {
        single<UserRepository> {
            UserRepositoryImpl(
                tokenManager = get(),
                userNetworkDataSource = get(),
            )
        }
    }
