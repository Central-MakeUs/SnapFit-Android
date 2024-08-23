package memory.fabricators.snapfit.network.user.di

import memory.fabricators.snapfit.network.user.UserNetworkDataSource
import memory.fabricators.snapfit.network.user.UserNetworkDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val userNetworkModule: Module
    get() = module {
        single<UserNetworkDataSource> {
            UserNetworkDataSourceImpl(
                httpClient = get(),
                tokenManager = get(),
            )
        }
    }
