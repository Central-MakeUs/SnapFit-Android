package memory.fabricators.snapfit.network

import memory.fabricators.snapfit.network.auth.di.authNetworkModule
import memory.fabricators.snapfit.network.login.di.loginNetworkModule
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module
    get() = module {
        includes(
            authNetworkModule,
            loginNetworkModule,
        )
    }
