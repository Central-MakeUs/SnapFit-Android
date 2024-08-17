package memory.fabricators.snapfit.network.login.di

import memory.fabricators.snapfit.network.login.LoginNetworkDataSource
import memory.fabricators.snapfit.network.login.LoginNetworkDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val loginNetworkModule: Module
    get() = module {
        single<LoginNetworkDataSource> {
            LoginNetworkDataSourceImpl(httpClient = get())
        }
    }
