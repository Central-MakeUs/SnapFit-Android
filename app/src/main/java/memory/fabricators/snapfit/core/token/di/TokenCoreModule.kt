package memory.fabricators.snapfit.core.token.di

import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.core.token.TokenManagerImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val tokenCoreModule: Module
    get() = module {
        single<TokenManager> {
            TokenManagerImpl(
                httpClient = get(),
                dataStore = get(),
            )
        }
    }

