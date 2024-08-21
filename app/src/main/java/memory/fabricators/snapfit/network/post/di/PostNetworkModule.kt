package memory.fabricators.snapfit.network.post.di

import memory.fabricators.snapfit.network.post.PostNetworkDataSource
import memory.fabricators.snapfit.network.post.PostNetworkDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val postNetworkModule: Module
    get() = module {
        single<PostNetworkDataSource> {
            PostNetworkDataSourceImpl(
                httpClient = get(),
                tokenManager = get(),
            )
        }
    }
