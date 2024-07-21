package memory.fabricators.snapfit.network.auth.di

import memory.fabricators.snapfit.network.auth.AuthNetworkDataSource
import memory.fabricators.snapfit.network.auth.AuthNetworkDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val authNetworkModule: Module
    get() = module {
        single<AuthNetworkDataSource> { AuthNetworkDataSourceImpl() }
    }
