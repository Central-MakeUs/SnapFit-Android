package memory.fabricators.snapfit.core.network.di

import io.ktor.client.HttpClient
import memory.fabricators.snapfit.core.network.httpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkCoreModule: Module
    get() = module {
        single<HttpClient> { httpClient }
    }
